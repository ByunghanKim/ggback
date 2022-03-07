package com.gg.ggback.service;

import com.gg.ggback.dto.ImageBoardDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ImageBoardService {

    List<ImageBoardDto> selectAllImage();
    int uploadImage(String title, String content, MultipartFile file);
}
