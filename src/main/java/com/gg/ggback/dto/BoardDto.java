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
public class BoardDto {

    private int num;
    private String title;
    private String writer;
    private String content;
    private Timestamp date;
    private int views;
    private int likes;
    private int reply_num;


}
