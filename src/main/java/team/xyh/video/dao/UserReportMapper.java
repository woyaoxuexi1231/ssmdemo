package team.xyh.video.dao;

import java.util.List;
import team.xyh.video.pojo.UserReport;

public interface UserReportMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserReport record);

    UserReport selectByPrimaryKey(String id);

    List<UserReport> selectAll();

    int updateByPrimaryKey(UserReport record);
}