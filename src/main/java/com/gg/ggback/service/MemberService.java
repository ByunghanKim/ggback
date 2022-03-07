package com.gg.ggback.service;

import com.gg.ggback.dto.MemberDto;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

public interface MemberService {

    List<MemberDto> selectAllMember();
    void insertMember(String id, String pw, String name, Timestamp reg_date);

}
