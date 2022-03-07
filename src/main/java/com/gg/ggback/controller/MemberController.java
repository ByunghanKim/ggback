package com.gg.ggback.controller;

import com.gg.ggback.dto.MemberDto;
import com.gg.ggback.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/selectmember")
    public List<MemberDto> getAllMember() {
        List<MemberDto> result = memberService.selectAllMember();
        return result;
    }

    @GetMapping("/insertmember")
    public void addMember() {
        memberService.insertMember("test","3456","park",new Timestamp(System.currentTimeMillis()));
    }
}
