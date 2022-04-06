package com.gg.ggback.service;

import com.gg.ggback.dto.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardService {

    List<BoardDto> selectAllContent(String tableName);

    int selectBoardTotalCount(String tableName);

    List<BoardDto> selectContents(String tableName, int limit, int offset);

    BoardDto getContentDetail(String tableName, int num);

    String insertBoard(String title,String content,String writer, String tableName);

    void deleteContent(String tableName, int num);

    void updateContent(String tableName, int num, String title, String content);



}
