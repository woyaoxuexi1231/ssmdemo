package team.xyh.video.dao;

import java.util.List;
import team.xyh.video.pojo.SearchRecord;

public interface SearchRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(SearchRecord record);

    SearchRecord selectByPrimaryKey(String id);

    List<SearchRecord> selectAll();

    int updateByPrimaryKey(SearchRecord record);
}