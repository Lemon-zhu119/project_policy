package com.ruoyi.web.controller.api.service.serviceImpl;

import com.ruoyi.web.controller.api.domain.Carousel;
import com.ruoyi.web.controller.api.mapper.CarouselMapper;
import com.ruoyi.web.controller.api.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public int insert(Carousel carousel) {
        return carouselMapper.insert(carousel);
    }



    @Override
    public Integer deleteById(Integer id) {
        return carouselMapper.deleteById(id);
    }

    @Override
    public int updateCarousel(Carousel carousel) {
        return carouselMapper.updateCarousel(carousel);
    }

    @Override
    public List<Carousel> selectCarouselList(Carousel carousel) {
        return carouselMapper.selectCarouselList(carousel);
    }

    @Override
    public List<Carousel> selectCarouselListById(Integer id) {
        return carouselMapper.selectCarouselListById(id);
    }


}
