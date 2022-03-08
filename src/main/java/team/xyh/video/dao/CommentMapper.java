package team.xyh.video.dao;

import java.util.List;
import team.xyh.video.pojo.Comment;

public interface CommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Comment record);

    Comment selectByPrimaryKey(String id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);
}