package team.xyh.video.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class VideoDto {
    private String id;
    private String user_id;
    private String video_path;
    private String video_desc;
    private String like_counts;
    private Integer status;

    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone="GMT+8")
    private Date create_time;

    public String getLike_counts() {
        return like_counts;
    }

    public void setLike_counts(String like_counts) {
        this.like_counts = like_counts;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideo_path() {
        return video_path;
    }

    public void setVideo_path(String video_path) {
        this.video_path = video_path;
    }

    public String getVideo_desc() {
        return video_desc;
    }

    public void setVideo_desc(String video_desc) {
        this.video_desc = video_desc;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
