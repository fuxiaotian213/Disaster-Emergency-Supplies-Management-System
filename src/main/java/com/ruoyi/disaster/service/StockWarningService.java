package com.ruoyi.disaster.service;

import com.ruoyi.disaster.domain.StockWarning;
import java.util.List;

/**
 * 库存预警Service接口
 */
public interface StockWarningService {
    // 按预警状态查询预警列表
    List<StockWarning> getStockWarningByStatus(Integer warningStatus);

    // 按物资ID查询预警
    StockWarning getStockWarningByMaterialId(Long materialId);

    // 查询所有预警
    List<StockWarning> getAllStockWarning();

    // 新增库存预警
    void addStockWarning(StockWarning stockWarning);

    // 标记预警为已处置
    void markAsHandled(Long id);

    // 删除预警
    void deleteStockWarningById(Long id);
}