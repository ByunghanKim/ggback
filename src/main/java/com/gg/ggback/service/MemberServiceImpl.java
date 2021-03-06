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
    public void insertMember(String id, String pw, String name) {

        MemberDto memberDto = new MemberDto();

        memberDto.setId(id);
        memberDto.setName(name);
        memberDto.setPw(bCryptPasswordEncoder.encode(pw));
        memberDto.setReg_date(new Timestamp(System.currentTimeMillis()));
        memberDto.setRoles("ROLE_USER");

        memberMapper.insertMember(memberDto);

    }

    @Override
    public int verifyId(String id) {

        int result = memberMapper.verifyId(id);

        return result;
    }
}
