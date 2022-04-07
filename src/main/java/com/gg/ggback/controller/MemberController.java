package com.gg.ggback.controller;

import com.gg.ggback.dto.MemberDto;
import com.gg.ggback.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin("*")
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

    @PostMapping("/join")
    public String join(
            @RequestParam("id") String id,
            @RequestParam("pw") String pw,
            @RequestParam("name") String name
            ) {

        memberService.insertMember(id,pw,name);

        return "회원가입 완료";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findMemberById(@PathVariable String id, HttpServletRequest request) {

        String reqId = (String) request.getAttribute("id");

        MemberDto dto = memberService.selectMember(id);

        if (reqId == null) {
            return new ResponseEntity<>("잘못된 접근 입니다.", HttpStatus.BAD_REQUEST);
        } else if (reqId.equals(dto.getId()) != true) {
            return new ResponseEntity<>("잘못된 접근 입니다.", HttpStatus.BAD_REQUEST);
        }

        dto.setPw("");

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/id")
    public ResponseEntity<Integer> verifyId(
            @RequestParam("id") String id
    ) {
        int result = memberService.verifyId(id);

        if(result > 0) {
            return new ResponseEntity<>(1,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(0,HttpStatus.OK);
        }

    }

}
