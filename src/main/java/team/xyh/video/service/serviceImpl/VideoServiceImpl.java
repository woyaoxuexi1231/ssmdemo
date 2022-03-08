package team.xyh.video.service.serviceImpl;

import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import team.xyh.video.dao.VideoMapper;
import team.xyh.video.dto.Result;
import team.xyh.video.dto.VideoDto;
import team.xyh.video.dto.VideoLikeDto;
import team.xyh.video.pojo.User;
import team.xyh.video.pojo.Video;
import team.xyh.video.service.VideoService;
import team.xyh.video.util.UpUtils;
import team.xyh.video.util.VideoUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    public List<Video> getAll() {

        return videoMapper.getAll();
    }

    @Override
    public List<VideoDto> getAll(Integer page, Integer limit) {
        return videoMapper.getAll(page,limit);
    }

    @Override
    public List<Video> getVideoById(String id,String videoDesc,Integer page,Integer limit,String createTimeRange,String startTime,String endTime) {
        return videoMapper.getVideoById(id,videoDesc,page,limit,createTimeRange,startTime,endTime);
    }

    @Override
    @Transactional
    public String deleteById(String id) {
            videoMapper.deleteByPrimaryKey(id);
            return "success";
    }

    @Override
    public Result update(String value,String id,String field) {
        Video video = videoMapper.selectByPrimaryKey(id);
        //判断当字段为点赞量的时候，判断参数是否为数字，如果为数字判断大小，最大为999
        //1.使用正则表达式 2.使用字符的判断方式
        System.out.println(video);
        System.out.println("字段名称："+field+" 字段值："+value+" ID："+id);
        Result result = new Result();
        if(video==null){
            result.setCode(500);
            return result;
        }
        if(field.equals("like_counts")){
            if(!value.matches("^[0-9]*[0-9]+$")){
                result.setMsg("点赞数只能是数字");
                return result;
            }else if(Integer.parseInt(value) > 999){
                value="999";
                result.setMsg("点赞量最大为999，已自动更改为999");
            }else {
                result.setMsg("更改成功");
            }
        }else {
            result.setMsg("更改成功");
        }
        try {
            videoMapper.update(value,id,field);
        }catch (Exception e){
            System.out.println(e);
            result.setMsg(e.toString());
            return result;
        }
        return result;
    }

    @Override
    public Video findVideoById(String id) {
        return videoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Long videoCount() {
        return videoMapper.videoCount();
    }

    @Override
    public List<String> findVideoUserId() {
        return videoMapper.findVideoUserId();
    }

    @Override
    public List<VideoDto> findVideoByUserId(String userId) {
        return videoMapper.findVideoByUserId(userId);
    }

    @Override
    public List<String> findVideoIdByUserId(String userId) {
        return videoMapper.findVideoIdByUserId(userId);
    }

    @Override
    public List<VideoDto> findVideoByUserIdAndId(String id) {
        return videoMapper.findVideoByUserIdAndId(id);
    }

    @Override
    public Result upload(MultipartFile file, HttpServletRequest request) {
        Result result = new Result();

        String mes =  UpUtils.upfile(file,request);
        if(mes.equals("200")){
           result.setMsg(file.getOriginalFilename());
           return result;
        }
        result.setCode(501);
        result.setMsg("上传完成");
        return result;

    }

    @Override
    @Transactional
    public Result addVideo(String videoPath, String videoDesc, HttpServletRequest request) {
        Video video = new Video();
        String id = UUID.randomUUID().toString();
        User user = (User) request.getSession().getAttribute("user");
        video.setUserId(user.getId());
        video.setId(id);
        video.setVideoDesc(videoDesc);
        video.setVideoPath(videoPath);
        video.setStatus(1);
        video.setLikeCounts((long)0);
        video.setCreateTime(new Date());
        //视频解析
        MultimediaInfo info = VideoUtils.analysis(videoPath,request);
        long duration = info.getDuration();
        int height = info.getVideo().getSize().getHeight();
        int width = info.getVideo().getSize().getWidth();
        video.setVideoSeconds(Float.valueOf(duration));
        video.setVideoHeight(height);
        video.setVideoWidth(width);
        Result result = new Result();
        try {
            result.setMsg("上传成功");
            videoMapper.insert(video);
            List<VideoDto> videos = videoMapper.getAll(1,10);
            result.setData(videos);
            return result;
        }catch (Exception e){
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setMsg("上传失败");
            return result;
        }
    }

    @Override
    public List<VideoLikeDto> getVideoLike() {
        return videoMapper.getVideoLike();
    }


}
