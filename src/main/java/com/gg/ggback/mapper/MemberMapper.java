package com.gg.ggback.mapper;

import com.gg.ggback.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface MemberMapper {

    List<MemberDto> selectAllMember();
    void insertMember(String id, String pw, String name, Timestamp reg_date);

}
