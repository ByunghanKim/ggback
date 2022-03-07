package com.gg.ggback.service;

import com.gg.ggback.dto.MemberDto;
import com.gg.ggback.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public List<MemberDto> selectAllMember() {

        List<MemberDto> result = memberMapper.selectAllMember();
        System.out.println("Select All Member 완료");
        return result;
    }

    @Override
    public void insertMember(String id, String pw, String name, Timestamp reg_date) {
        memberMapper.insertMember(id,pw,name,reg_date);
    }
}
