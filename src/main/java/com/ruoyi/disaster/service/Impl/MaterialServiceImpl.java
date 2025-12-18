package com.ruoyi.disaster.service.Impl;

import com.ruoyi.disaster.domain.Material;
import com.ruoyi.disaster.mapper.MaterialMapper;
import com.ruoyi.disaster.service.MaterialService;
import com.ruoyi.disaster.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

/**
 * 救援物资Service实现类
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public List<Material> getMaterialList() {
        return materialMapper.queryAll();
    }

    @Override
    public Material getMaterialById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("物资ID不能为空");
        }
        Material material = materialMapper.queryById(id);
        if (ObjectUtils.isEmpty(material)) {
            throw new ServiceException("未查询到ID为" + id + "的物资");
        }
        return material;
    }

    @Override
    public List<Material> getMaterialByIds(Set<Long> ids) {
        if (ObjectUtils.isEmpty(ids) || ids.isEmpty()) {
            throw new ServiceException("查询ID集合不能为空");
        }
        return materialMapper.queryByIds(ids);
    }

    @Override
    public List<Material> getMaterialByCondition(Integer materialType, Integer priorityLevel, Long reservePointId) {
        return materialMapper.queryByCondition(materialType, priorityLevel, reservePointId);
    }

    @Override
    public void addMaterial(Material material) {
        // 参数校验
        if (ObjectUtils.isEmpty(material.getMaterialName())) {
            throw new ServiceException("物资名称不能为空");
        }
        if (ObjectUtils.isEmpty(material.getMaterialType())) {
            throw new ServiceException("物资类型不能为空");
        }
        if (ObjectUtils.isEmpty(material.getReservePointId())) {
            throw new ServiceException("储备点ID不能为空");
        }
        if (material.getStockNum() == null || material.getStockNum() < 0) {
            throw new ServiceException("库存数量不能为负数");
        }
        materialMapper.saveMaterial(material);
    }

    @Override
    public void modifyMaterial(Material material) {
        // 参数校验
        if (ObjectUtils.isEmpty(material.getId())) {
            throw new ServiceException("物资ID不能为空");
        }
        // 校验物资是否存在
        getMaterialById(material.getId());
        // 库存数量校验
        if (material.getStockNum() != null && material.getStockNum() < 0) {
            throw new ServiceException("库存数量不能为负数");
        }
        materialMapper.updateMaterial(material);
    }

    @Override
    public void updateMaterialStock(Long id, Integer newStockNum) {
        // 参数校验
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("物资ID不能为空");
        }
        if (newStockNum == null || newStockNum < 0) {
            throw new ServiceException("库存数量不能为负数");
        }
        // 校验物资是否存在
        getMaterialById(id);
        materialMapper.updateStock(id, newStockNum);
    }

    @Override
    public void deleteMaterialById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("物资ID不能为空");
        }
        // 校验物资是否存在
        getMaterialById(id);
        materialMapper.deleteMaterial(id);
    }

    @Override
    public int deleteMaterialByIds(Set<Long> ids) {
        if (ObjectUtils.isEmpty(ids) || ids.isEmpty()) {
            throw new ServiceException("删除ID集合不能为空");
        }
        return materialMapper.deleteMaterials(ids);
    }
}