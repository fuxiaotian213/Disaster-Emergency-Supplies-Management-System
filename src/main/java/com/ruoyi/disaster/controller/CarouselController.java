package com.ruoyi.disaster.controller;

import com.ruoyi.disaster.domain.Carousel;
import com.ruoyi.disaster.service.CarouselService;

import com.ruoyi.disaster.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.disaster.util.Anonymous;

import java.util.List;

/**
 * 轮播图Controller
 */
@RestController
@RequestMapping("/carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @Anonymous
    @RequestMapping(value = "/getAllCarousel", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getAllCarousel() {
        List<Carousel> list = carouselService.getAllCarouselList();
        return AjaxResult.success(list);
    }

    // 2. 新增：按ID查询轮播图（编辑时用，前端调用 /carousel/getCarouselById?id=xxx）
    @Anonymous
    @RequestMapping(value = "/getCarouselById", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getCarouselById(Long id) {
        Carousel carousel = carouselService.getCarouselById(id);
        // 反向映射 status 到 enabled，适配前端
        carousel.setEnabled(carousel.getStatus());
        return AjaxResult.success(carousel);
    }

    // 查询启用的轮播图（前端展示）
    @Anonymous
    @RequestMapping(value = "/getEnabledList", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getEnabledList() {
        List<Carousel> list = carouselService.getEnabledCarouselList();
        return AjaxResult.success(list);
    }

    // 新增轮播图
    @Anonymous
    @RequestMapping(value = "/addCarousel", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult addCarousel(@RequestBody Carousel carousel) {
        carouselService.addCarousel(carousel);
        return AjaxResult.success("轮播图新增成功");
    }

    // 修改轮播图
    @Anonymous
    @RequestMapping(value = "/updateCarousel", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult updateCarousel(@RequestBody Carousel carousel) throws Exception {
        // 1. 状态映射：前端enabled → 后端status（确保启用/禁用正常生效）
        if (carousel.getEnabled() != null) {
            carousel.setStatus(carousel.getEnabled());
        }

        // 2. 图片处理核心逻辑：上传新图用新地址，不上传则保留旧地址
        if (carousel.getImageUrl() != null && !carousel.getImageUrl().isEmpty()) {
            // 若前端传递了新图片地址（如重新上传），直接使用
            carousel.setImageUrl(carousel.getImageUrl());
        } else {
            // 关键：未上传新图时，使用前端传递的oldImageUrl保留旧图
            carousel.setImageUrl(carousel.getOldImageUrl());
        }

        carouselService.modifyCarousel(carousel);
        return AjaxResult.success("轮播图修改成功");
    }

    // 删除轮播图
    @Anonymous
    @RequestMapping(value = "/deleteCarousel", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public AjaxResult deleteCarousel(Long id) {
        carouselService.deleteCarouselById(id);
        return AjaxResult.success("轮播图删除成功");
    }
}