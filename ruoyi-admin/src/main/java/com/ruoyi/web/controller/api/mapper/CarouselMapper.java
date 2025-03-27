package com.ruoyi.web.controller.api.mapper;

import com.ruoyi.web.controller.api.domain.Carousel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarouselMapper {

    int insert(Carousel carousel);




    Integer deleteById(Integer id);


    int updateCarousel(Carousel carousel);

    List<Carousel> selectCarouselList(Carousel carousel);

    List<Carousel> selectCarouselListById(Integer id);
}
