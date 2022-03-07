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
public class ImageBoardDto {
    private String title;
    private Timestamp date;
    private String content;
    private String img_origin_name;
    private String img_name;
}
