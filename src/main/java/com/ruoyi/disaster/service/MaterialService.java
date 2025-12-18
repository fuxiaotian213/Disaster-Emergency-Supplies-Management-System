package com.ruoyi.disaster.service;

import com.ruoyi.disaster.domain.Material;
import java.util.List;
import java.util.Set;

/**
 * 救援物资Service接口
 */
public interface MaterialService {
    // 1. 查询所有物资列表
    List<Material> getMaterialList();

    // 2. 通过ID查询物资详情
    Material getMaterialById(Long id);

    // 3. 批量查询物资（支持多ID同时查询）
    List<Material> getMaterialByIds(Set<Long> ids);

    // 4. 按条件查询物资（类型/优先级/储备点）
    List<Material> getMaterialByCondition(Integer materialType, Integer priorityLevel, Long reservePointId);

    // 5. 添加物资
    void addMaterial(Material material);

    // 6. 修改物资信息
    void modifyMaterial(Material material);

    // 7. 更新物资库存（支持增减库存）
    void updateMaterialStock(Long id, Integer newStockNum);

    // 8. 删除物资（单条）
    void deleteMaterialById(Long id);

    // 9. 批量删除物资（支持多ID同时删除）
    int deleteMaterialByIds(Set<Long> ids);
}