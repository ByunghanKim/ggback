package com.gg.ggback.service;

import com.gg.ggback.dto.MemberDto;
import com.gg.ggback.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    final MemberMapper memberMapper;

    final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberMapper = memberMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<MemberDto> selectAllMember() {

        List<MemberDto> result = memberMapper.selectAllMember();
        System.out.println("Select All Member 완료");
        return result;
    }

    @Override
    public MemberDto selectMember(String id) {

        MemberDto dto = memberMapper.selectMember(id);

        return dto;
    }

    @Override
    public void insertMember(MemberDto memberDto) {

        memberDto.setPw(bCryptPasswordEncoder.encode(memberDto.getPw()));
        memberDto.setReg_date(new Timestamp(System.currentTimeMillis()));
        memberDto.setRoles("ROLE_USER");

        memberMapper.insertMember(memberDto);

    }
}
