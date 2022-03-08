package team.xyh.video.dao;

import java.util.List;
import team.xyh.video.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    User selectByPrimaryKey(String id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User findUserByName(String userName);
}