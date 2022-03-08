package team.xyh.video.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.xyh.video.dto.Result;
import team.xyh.video.dto.VideoDto;
import team.xyh.video.dto.VideoLikeDto;
import team.xyh.video.pojo.Video;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public interface VideoService {
    List<Video> getAll();

    List<VideoDto> getAll(Integer page, Integer limit);

    List<Video> getVideoById(String id,String videoDesc,Integer page,Integer limit,String createTimeRange,String startTime,String endTime);

    String deleteById(String id);

    Result update(String value,String id,String filed);

    Video findVideoById(String id);

    Long videoCount();

    List<String> findVideoUserId();

    List<VideoDto> findVideoByUserId(String userId);

    List<String> findVideoIdByUserId(String userId);

    List<VideoDto> findVideoByUserIdAndId(String id);

    Result upload(MultipartFile file, HttpServletRequest request);

    Result addVideo(String videoPath, String videoDesc, HttpServletRequest request);

    List<VideoLikeDto> getVideoLike();
}
