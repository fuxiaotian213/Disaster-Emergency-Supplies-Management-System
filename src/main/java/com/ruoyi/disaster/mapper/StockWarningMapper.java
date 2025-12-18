package com.ruoyi.disaster.mapper;

import com.ruoyi.disaster.domain.StockWarning;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 库存预警Mapper接口
 */
public interface StockWarningMapper {
    // 按预警状态查询预警列表
    List<StockWarning> queryByWarningStatus(Integer warningStatus);
    // 按物资ID查询预警
    StockWarning queryByMaterialId(Long materialId);
    // 新增预警
    void saveStockWarning(StockWarning stockWarning);
    // 更新预警处置状态
    void updateHandleStatus(@Param("id") Long id, @Param("handleStatus") Integer handleStatus);

    List<StockWarning> queryAll();

    void deleteStockWarning(Long id);

    StockWarning queryById(Long id);
}