package com.ruoyi.disaster.mapper;

import com.ruoyi.disaster.domain.Material;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Set;

/**
 * 救援物资Mapper接口（对应 biz_material 表）
 */
public interface MaterialMapper {
    // 1. 查询所有物资列表
    List<Material> queryAll();

    // 2. 通过ID查询物资详情
    Material queryById(Long id);

    // 3. 批量查询物资（支持多ID同时查询）
    List<Material> queryByIds(@Param("ids") Set<Long> ids);

    // 4. 按条件查询物资（支持类型、优先级、储备点筛选）
    List<Material> queryByCondition(@Param("materialType") Integer materialType,
                                    @Param("priorityLevel") Integer priorityLevel,
                                    @Param("reservePointId") Long reservePointId);

    // 5. 添加物资
    void saveMaterial(Material material);

    // 6. 修改物资信息
    void updateMaterial(Material material);

    // 7. 更新物资库存
    void updateStock(@Param("id") Long id, @Param("newStockNum") Integer newStockNum);

    // 8. 删除物资（单条）
    void deleteMaterial(Long id);

    // 9. 批量删除物资（支持多ID同时删除）
    int deleteMaterials(@Param("ids") Set<Long> ids);
}