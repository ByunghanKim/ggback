package com.gg.ggback.service;

import com.gg.ggback.dto.ImageBoardDto;
import com.gg.ggback.mapper.ImageBoardMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class ImageBoardServiceImpl implements ImageBoardService{

    final ImageBoardMapper imageBoardMapper;

    String imagePath = "c:\\ggWebImages/";


    @Autowired
    public ImageBoardServiceImpl(ImageBoardMapper imageBoardMapper) {
        this.imageBoardMapper = imageBoardMapper;
    }

    @Override
    public List<ImageBoardDto> selectAllImage() {
        List<ImageBoardDto> ibd = imageBoardMapper.selectAllImage();
        return ibd;
    }

    @Override
    public int uploadImage(String title, String content, MultipartFile file) {


        File pfile = new File(imagePath);

        Timestamp ts = new Timestamp(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();

        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        ImageBoardDto dto = new ImageBoardDto();

        dto.setTitle(title);
        dto.setContent(content);
        dto.setDate(ts);
        dto.setImg_origin_name(file.getOriginalFilename());
        dto.setImg_name(uuid +"." + extension);

        try {

            imageBoardMapper.insertImage(dto);

            if(pfile.exists() == false) {
                pfile.mkdirs();
            }

            file.transferTo(new File(imagePath + uuid +"." + extension));

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("이미지 업로드 에러");
            return 0;
        }
        return 1;
    }

    @Override
    public byte[] findImageById(String id) throws IOException {
        InputStream imageStream = new FileInputStream(imagePath + id);

        byte[] imgByteArray = IOUtils.toByteArray(imageStream);

        imageStream.close();
        return imgByteArray;
    }


}
