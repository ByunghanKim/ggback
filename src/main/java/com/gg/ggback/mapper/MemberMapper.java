package com.gg.ggback.mapper;

import com.gg.ggback.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface MemberMapper {

    List<MemberDto> selectAllMember();
    MemberDto selectMember(String id);
    void insertMember(MemberDto memberDto);
    int verifyId(String id);

}
