package team.xyh.video.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;
import team.xyh.video.dto.Result;
import team.xyh.video.dto.VideoDto;
import team.xyh.video.dto.VideoLikeDto;
import team.xyh.video.pojo.Video;

public interface VideoMapper {
    int deleteByPrimaryKey(String id);

    int insert(Video record);

    Video selectByPrimaryKey(String id);

    List<Video> selectAll();

    int updateByPrimaryKey(Video record);

    List<Video> getAll();

    List<VideoDto> getAll(@Param("page") Integer page, @Param("limit") Integer limit);

    List<Video> getVideoById(@Param("id") String id, @Param("videoDesc") String videoDesc, @Param("page") Integer page, @Param("limit") Integer limit,@Param("createTimeRange") String createTimeRange,@Param("startTime") String startTime,@Param("endTime") String endTime);

    void update(@Param("value") String value,@Param("id") String id,@Param("field") String field);

    Long videoCount();

    Result delete(String[] ids);

    void addVideo(@Param("id")String id, @Param("userId") String userId, @Param("createTime") Date createTime, @Param("videoPath") String videoPath, @Param("likeCounts") Long likeCounts, @Param("status") Integer status, @Param("videoDesc") String videoDesc);

    List<String> findVideoUserId();

    List<VideoDto> findVideoByUserId(String userId);

    List<String> findVideoIdByUserId(String userId);

    List<VideoDto> findVideoByUserIdAndId(String id);

    Result upload(MultipartFile file);

    List<VideoLikeDto> getVideoLike();
}