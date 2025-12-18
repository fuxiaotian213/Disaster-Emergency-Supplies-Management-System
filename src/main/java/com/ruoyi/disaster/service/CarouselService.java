package com.ruoyi.disaster.service;

import com.ruoyi.disaster.domain.Carousel;
import java.util.List;

/**
 * 轮播图Service接口
 */
public interface CarouselService {
    // 查询启用的轮播图列表（前端展示用）
    List<Carousel> getEnabledCarouselList();

    // 按ID查询轮播图
    Carousel getCarouselById(Long id);

    // 查询所有轮播图（管理端用）
    List<Carousel> getAllCarouselList();

    // 新增轮播图
    void addCarousel(Carousel carousel);

    // 修改轮播图
    void modifyCarousel(Carousel carousel);

    // 删除轮播图
    void deleteCarouselById(Long id);
}