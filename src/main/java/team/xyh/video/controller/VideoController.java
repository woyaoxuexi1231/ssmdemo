package team.xyh.video.controller;


import com.sun.xml.internal.ws.developer.Serialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import team.xyh.video.dto.Result;
import team.xyh.video.dto.VideoDto;
import team.xyh.video.dto.VideoLikeDto;
import team.xyh.video.pojo.Video;
import team.xyh.video.service.VideoService;
import team.xyh.video.service.serviceImpl.VideoServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/videos")
@Scope("prototype")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping("/getAll") /*路径映射*/
    @ResponseBody /*自动把数据转换成json格式*/
    /*在请求中添加两个属性page和limit*/
    public Result getAll(Integer page, Integer limit){

        if(page!=null&&page>=0){
            page = (page-1)*limit;
        }else{
            page=1;
        }
        if(limit==null){
            limit = 3;
        }else {
            limit = page+limit;
        }
        System.out.println(page+"---"+limit);
        List<VideoDto> videos = videoService.getAll(page,limit);
        Result result = new Result();
        result.setData(videos);
        result.setCode(0);
        result.setCount(videoService.videoCount());
        return result;
    }

    @RequestMapping("/getVideoById")
    @ResponseBody
    public List<Video> getVideoById(String id,String videoDesc,Integer page,Integer limit,String createTimeRange,String startTime,String endTime){
            return videoService.getVideoById(id,videoDesc,page,limit,createTimeRange,startTime,endTime);
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteById(String id){
        System.out.println("要删除的ID为："+id);
        try {
            videoService.deleteById(id);
            return "success";
        }
        catch (Exception e){
            return "error";
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(String[] ids){
        Result result = new Result();
        try {
            for (String id : ids) {
                System.out.println("删除的ID："+id);
                videoService.deleteById(id);
            }
            result.setMsg("success");
        }
        catch (Exception e){
            result.setMsg("error");
            return result;
        }
        return result;
    }

    @RequestMapping("update")
    @ResponseBody
    public Result update(String value,String id,String field){
        return videoService.update(value,id,field);
    }

    @RequestMapping("findVideoById")
    @ResponseBody
    public Video findVideoById(String id){
        System.out.println(id);
        return videoService.findVideoById(id);
    }

    @RequestMapping("selectByLike")
    @ResponseBody
    public Result selectByLike(String id,String videoDesc,Integer page,Integer limit,String createTimeRange){
        if((id==null||id=="")&&(videoDesc==null||videoDesc=="")&&(createTimeRange==null||createTimeRange=="")){
            return getAll(page,limit);
        }
        if(page!=null&&page>=0){
            page = (page-1)*limit;
        }else{
            page=1;
        }
        if(limit==null){
            limit = 3;
        }else {
            limit = page+limit;
        }
        System.out.println(createTimeRange);
        if(createTimeRange!=null);
        String startTime = "1999-01-01 00:00:00";
        String endTime ="2090-01-01 00:00:00";
        if(createTimeRange!=null&&createTimeRange!=""){
            startTime = createTimeRange.substring(0,19);
            endTime = createTimeRange.substring(22,41);
        }
        System.out.println("id:"+id+" videoDesc:"+videoDesc+" page:"+page+" limit:"+limit+" timeRange:"+createTimeRange);
        List<Video> videos = videoService.getVideoById(id,videoDesc,page,limit,createTimeRange,startTime,endTime);
        System.out.println(videos);
        Result result = new Result();
        result.setData(videos);
        result.setCode(0);
        result.setCount((long)videos.size());
        result.setMsg("查询成功");
        return result;
    }

    @RequestMapping("/addVideo")
    @ResponseBody
    public Result addVideo(String videoDesc,String videoPath,HttpServletRequest request) throws ParseException {

       return videoService.addVideo(videoPath,videoDesc,request);

    }

    @RequestMapping("/findVideoUserId")
    @ResponseBody
    public List<String> findVideoUserId(){
        return videoService.findVideoUserId();
    }

    @RequestMapping("/findVideoIdByUserId")
    @ResponseBody
    public List<String> findVideoIdByUserId(String userId){
        System.out.println(userId);
        return videoService.findVideoIdByUserId(userId);
    }

    @RequestMapping("/findVideoByUserId")
    @ResponseBody
    public Result findVideoByUserId(String userId){
        System.out.println(userId);
        Result result = new Result();
        List<VideoDto> videos = videoService.findVideoByUserId(userId);
        result.setData(videos);
        result.setMsg("success");
        result.setCode(0);
        result.setCount((long)videos.size());
        return result;
    }

    @RequestMapping("/findVideoByUserIdAndId")
    @ResponseBody
    public Result findVideoByUserIdAndId(String id){
        System.out.println(id);
        Result result = new Result();
        List<VideoDto> videos = videoService.findVideoByUserIdAndId(id);
        result.setData(videos);
        result.setMsg("success");
        result.setCode(0);
        result.setCount((long)videos.size());
        return result;
    }


    @RequestMapping("/upload")
    @ResponseBody
    public Result upload(MultipartFile file, HttpServletRequest request) throws ParseException {
        Result result = videoService.upload(file,request);
//        Date date=new Date();
//        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        addVideo("1112324525","2222",df.format(date),result.getMsg(),(long)0,0,"123124");
        return result;
    }

    @RequestMapping("/getVideoLike")
    @ResponseBody
    public List<VideoLikeDto> getVideoLike(){
            return videoService.getVideoLike();
    }
}
