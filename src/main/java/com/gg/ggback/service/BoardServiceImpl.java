package com.gg.ggback.service;

import com.gg.ggback.dto.BoardDto;
import com.gg.ggback.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public List<BoardDto> selectAllContent(String tableName) {

        List<BoardDto> dto = boardMapper.selectAllContent(tableName);

        return dto;
    }

    @Override
    public int selectBoardTotalCount(String tableName) {

        int count = boardMapper.selectBoardTotalCount(tableName);

        return count;
    }

    @Override
    public List<BoardDto> selectContents(String tableName, int limit, int offset) {

        Map map = new HashMap<>();

        map.put("tableName", tableName);
        map.put("limit",limit);
        map.put("offset",offset * limit);

        List<BoardDto> dto = boardMapper.selectContents(map);

        return dto;
    }

    @Override
    public BoardDto getContentDetail(String tableName, int num) {

        Map map = new HashMap<>();

        map.put("tableName",tableName);
        map.put("num",num);

        BoardDto dto = boardMapper.getContentDetail(map);

        String tmp = dto.getContent();

        tmp.replaceAll("<br>","\r\n");

        dto.setContent(tmp);

        if(dto != null) {
            boardMapper.increaseView(map);
        }

        return dto;
    }

    @Override
    public String insertBoard(String title, String content, String writer, String tableName) {

        Map map = new HashMap<>();

        BoardDto dto = new BoardDto();

        dto.setTitle(title);
        dto.setContent(content);
        System.out.println(content);
        dto.setWriter(writer);
        dto.setDate(new Timestamp(System.currentTimeMillis()));

        map.put("tableName", tableName);
        map.put("boardDto", dto);

        int result = boardMapper.insertBoard(map);

        return result + "";
    }

    @Override
    public void deleteContent(String tableName, int num) {
        Map map = new HashMap<>();

        map.put("tableName", tableName);
        map.put("num", num);

        boardMapper.deleteContent(map);

    }

    @Override
    public void updateContent(String tableName, int num, String title, String content) {
        Map map = new HashMap<>();

        map.put("tableName", tableName);
        map.put("num", num);
        map.put("title",title);
        map.put("content", content);

        boardMapper.updateContent(map);
    }
}
