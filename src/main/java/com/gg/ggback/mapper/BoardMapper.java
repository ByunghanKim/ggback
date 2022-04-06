package com.gg.ggback.mapper;

import com.gg.ggback.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    List<BoardDto> selectAllContent(String tableName);
    int selectBoardTotalCount(String tableName);
    List<BoardDto> selectContents(Map map);
    BoardDto getContentDetail(Map map);
    int insertBoard(Map map);
    void increaseView(Map map);
    void deleteContent(Map map);
    void updateContent(Map map);


}
