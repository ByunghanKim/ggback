package com.gg.ggback.controller;

import com.gg.ggback.dto.MemberDto;
import com.gg.ggback.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/member")
public class MemberController {

    final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public List<MemberDto> getAllMember() {
        List<MemberDto> result = memberService.selectAllMember();
        return result;
    }

    @PostMapping("/")
    public void addMember() {
        memberService.insertMember("test","3456","park",new Timestamp(System.currentTimeMillis()));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberDto memberDto) {

        System.out.println(memberDto.getId());
        System.out.println(memberDto.getPw());

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
