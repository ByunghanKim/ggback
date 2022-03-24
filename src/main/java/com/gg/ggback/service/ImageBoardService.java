package com.gg.ggback.service;

import com.gg.ggback.dto.ImageBoardDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ImageBoardService {

    //모든 이미지 조회
    List<ImageBoardDto> selectAllImage();

    //이미지 업로드
    int uploadImage(String title, String content, MultipartFile file);

    //이미지 이름으로 찾기 (UUID)
    byte[] findImageById(String id) throws IOException;
}
