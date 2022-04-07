package com.gg.ggback.service;

import com.gg.ggback.dto.MemberDto;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

public interface MemberService {

    //모든 회원 찾기
    List<MemberDto> selectAllMember();

    //아이디로 회원 한명 찾기
    MemberDto selectMember(String id);

    //회원 추가
    void insertMember(String id, String pw, String name);

    //회원 아이디 조회
    int verifyId(String id);

}
