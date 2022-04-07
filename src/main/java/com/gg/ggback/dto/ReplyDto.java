package com.gg.ggback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {

    private int num;
    private int content_num;
    private String id;
    private Timestamp date;
    private String content;

}
