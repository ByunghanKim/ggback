package com.gg.ggback.controller;

import com.gg.ggback.dto.ImageBoardDto;
import com.gg.ggback.service.ImageBoardService;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/imgboard")
public class ImageBoardController {

    private final ImageBoardService imageBoardService;

    public ImageBoardController(ImageBoardService imageBoardService) {
        this.imageBoardService = imageBoardService;
    }

    @GetMapping("/load")
    public List<ImageBoardDto> loadPage() {
        List<ImageBoardDto> ibd = imageBoardService.selectAllImage();
        return ibd;
    }

    @GetMapping("img/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) throws IOException {
        InputStream imageStream = new FileInputStream("C:\\boottest/" + id);
        byte[] imgByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();

        return new ResponseEntity<byte[]>(imgByteArray, HttpStatus.OK);
    }

    @PostMapping("img/upload")
    public ResponseEntity<String> uploadImageFile(
            @RequestParam("image") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("content") String content) {

        int result = imageBoardService.uploadImage(title,content,file);

        if(result == 1) {
            return new ResponseEntity<>("OK",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("IMAGE_UPLOAD_FAIL",HttpStatus.BAD_REQUEST);
        }

    }
}
