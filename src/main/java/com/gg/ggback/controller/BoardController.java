package com.gg.ggback.controller;

import com.gg.ggback.dto.BoardDto;
import com.gg.ggback.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/load")
    public List<BoardDto> loadBoard(@RequestParam("boardType") String boardType) {

        String tableName = boardType + "_board";

        List<BoardDto> dto = boardService.selectAllContent(tableName);

        return dto;
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteContent(
            @RequestParam("boardType") String boardType,
            @RequestParam("num") int num
    ) {

        String tableName = boardType + "_board";

        boardService.deleteContent(tableName, num);

        return new ResponseEntity<>("GOOD", HttpStatus.OK);
    }

    @PostMapping("/update")
    public BoardDto selectForUpdateContent(
            @RequestParam("boardType") String boardType,
            @RequestParam("num") int num,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {

        String tableName = boardType + "_board";

        boardService.updateContent(tableName, num, title, content);

        return null;
    }

    @GetMapping("/freeboard/load")
    public ResponseEntity<List<BoardDto>> loadNoticeBoard(
            @RequestParam("boardType") String boardType,
            @RequestParam("limit") int limit,
            @RequestParam("offset") int offset
    ) {

        String tableName = boardType + "_board";

        List<BoardDto> dto = boardService.selectContents(tableName, limit, offset);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/freeboard/num")
    public ResponseEntity<Integer> getContentCount(@RequestParam("boardType") String boardType) {

        String tableName = boardType + "_board";

        int result = boardService.selectBoardTotalCount(tableName);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/freeboard/detail")
    public ResponseEntity<BoardDto> getContentDetail(
            @RequestParam("boardType") String boardType,
            @RequestParam("num") int num
    ) {

        String tableName = boardType + "_board";

        BoardDto dto = boardService.getContentDetail(tableName, num);

        return new ResponseEntity<>(dto,HttpStatus.OK);

    }

    @PostMapping("/freeboard/insert")
    public ResponseEntity<String> insertBoard(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("writer") String writer,
            @RequestParam("boardType") String boardType
    ) {
        String tableName = boardType + "_board";

        String result = boardService.insertBoard(title,content,writer,tableName);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
