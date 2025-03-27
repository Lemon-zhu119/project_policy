package com.ruoyi.web.controller.api.controller;

import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Put;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.controller.api.common.Result;
import com.ruoyi.web.controller.api.domain.Carousel;
import com.ruoyi.web.controller.api.service.CarouselService;
import com.ruoyi.web.controller.company.domain.CompanyReport;
import com.ruoyi.web.controller.company.service.ICompanyReportService;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

@CrossOrigin
@RestController
@RequestMapping("/api/carousel")
public class CarouselController extends BaseController {
    @Autowired
    private CarouselService carouselService;

    @PostMapping("/insert")
    @ResponseBody
    public Result<String> insertCarousel(@RequestBody Carousel carousel){
        System.out.print(carousel);
         int carouseldb= carouselService.insert(carousel);
         return Result.success("插入成功");
    }
    @GetMapping("/CarouselList")
    public TableDataInfo list(Carousel carousel){
        startPage();
        return getDataTable(carouselService.selectCarouselList(carousel));
    }
    @GetMapping("/CarouselListById")
    public TableDataInfo listById(@RequestParam Integer id){
        startPage();
        return getDataTable(carouselService.selectCarouselListById(id));
    }
    @DeleteMapping("/DelCarousel/{id}")
    private Result DelCarousel(@PathVariable Integer id){
        Integer delcarousel=carouselService.deleteById(id);
        if(delcarousel==1){
            return Result.success("删除成功");
        }else{
            return Result.error("删除失败");
        }
    }
    @PutMapping("/updateCarousel")
    public Result<Integer> updateCarousel(@RequestBody Carousel carousel){
        int updateRow=carouselService.updateCarousel(carousel);
        return Result.success(updateRow);
    }
}
