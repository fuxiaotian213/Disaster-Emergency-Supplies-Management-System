package com.ruoyi.disaster.mapper;

import com.ruoyi.disaster.domain.Carousel;
import java.util.List;

/**
 * 轮播图Mapper接口（补充queryAll()方法）
 */
public interface CarouselMapper {
    // 查询启用的轮播图列表
    List<Carousel> queryEnabledList();
    // 按ID查询轮播图
    Carousel queryById(Long id);
    // 查询所有轮播图（管理端用，新增此方法）
    List<Carousel> queryAll();
    // 新增轮播图
    void saveCarousel(Carousel carousel);
    // 修改轮播图
    void updateCarousel(Carousel carousel);
    // 删除轮播图
    void deleteCarousel(Long id);
}