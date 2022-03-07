package com.gg.ggback.mapper;

import com.gg.ggback.dto.ImageBoardDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ImageBoardMapper {

    List<ImageBoardDto> selectAllImage();
    void insertImage(ImageBoardDto dto);

}
