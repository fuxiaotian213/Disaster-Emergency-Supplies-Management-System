package com.ruoyi.disaster.service.Impl;

import com.ruoyi.disaster.domain.Carousel;
import com.ruoyi.disaster.mapper.CarouselMapper;
import com.ruoyi.disaster.service.CarouselService;
import com.ruoyi.disaster.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 轮播图Service实现类
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> getEnabledCarouselList() {
        return carouselMapper.queryEnabledList();
    }

    @Override
    public Carousel getCarouselById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("轮播图ID不能为空");
        }
        Carousel carousel = carouselMapper.queryById(id);
        if (ObjectUtils.isEmpty(carousel)) {
            throw new ServiceException("未查询到ID为" + id + "的轮播图");
        }
        return carousel;
    }

    @Override
    public List<Carousel> getAllCarouselList() {
        return carouselMapper.queryAll();
    }

    @Override
    public void addCarousel(Carousel carousel) {
        // 参数校验（移除content非空校验）
        if (ObjectUtils.isEmpty(carousel.getTitle())) {
            throw new ServiceException("轮播标题不能为空");
        }
        if (ObjectUtils.isEmpty(carousel.getImageUrl())) {
            throw new ServiceException("图片地址不能为空");
        }
        if (ObjectUtils.isEmpty(carousel.getSort())) {
            throw new ServiceException("显示顺序不能为空");
        }
        if (ObjectUtils.isEmpty(carousel.getPublishDept())) {
            throw new ServiceException("发布部门不能为空");
        }
        // 默认状态为启用
        if (ObjectUtils.isEmpty(carousel.getStatus())) {
            carousel.setStatus(1);
        }
        carouselMapper.saveCarousel(carousel);
    }





    @Override
    public void modifyCarousel(Carousel carousel) {
        // 新增：校验轮播图ID和状态不为空
        if (ObjectUtils.isEmpty(carousel.getId())) {
            throw new ServiceException("轮播图ID不能为空");
        }
        if (ObjectUtils.isEmpty(carousel.getStatus())) {
            throw new ServiceException("轮播图状态不能为空");
        }
        // 原有逻辑：校验轮播图是否存在
        Carousel existCarousel = getCarouselById(carousel.getId());
        if (ObjectUtils.isEmpty(existCarousel)) {
            throw new ServiceException("未查询到该轮播图");
        }
        carouselMapper.updateCarousel(carousel);
    }

    @Override
    public void deleteCarouselById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("轮播图ID不能为空");
        }
        getCarouselById(id);
        carouselMapper.deleteCarousel(id);
    }
}