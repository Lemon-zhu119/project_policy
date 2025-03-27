package com.ruoyi.web.controller.api.service;

import com.ruoyi.web.controller.api.domain.Carousel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarouselService {

    int insert(Carousel carousel);




    Integer deleteById(Integer id);


    int updateCarousel(Carousel carousel);

    List<Carousel> selectCarouselList(Carousel carousel);


    List<Carousel> selectCarouselListById(Integer id);
}
