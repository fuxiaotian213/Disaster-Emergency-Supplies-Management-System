package com.ruoyi.disaster.service.Impl;

import com.ruoyi.disaster.domain.Material;
import com.ruoyi.disaster.domain.StockWarning;
import com.ruoyi.disaster.mapper.MaterialMapper;
import com.ruoyi.disaster.mapper.StockWarningMapper;
import com.ruoyi.disaster.service.StockWarningService;
import com.ruoyi.disaster.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 库存预警Service实现类
 */
@Service
public class StockWarningServiceImpl implements StockWarningService {

    @Autowired
    private StockWarningMapper stockWarningMapper;

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public List<StockWarning> getStockWarningByStatus(Integer warningStatus) {
        if (ObjectUtils.isEmpty(warningStatus)) {
            throw new ServiceException("预警状态不能为空");
        }
        return stockWarningMapper.queryByWarningStatus(warningStatus);
    }

    @Override
    public StockWarning getStockWarningByMaterialId(Long materialId) {
        if (ObjectUtils.isEmpty(materialId)) {
            throw new ServiceException("物资ID不能为空");
        }
        return stockWarningMapper.queryByMaterialId(materialId);
    }

    @Override
    public List<StockWarning> getAllStockWarning() {
        return stockWarningMapper.queryAll();
    }

    @Override
    public void addStockWarning(StockWarning stockWarning) {
        // 参数校验
        if (ObjectUtils.isEmpty(stockWarning.getMaterialId())) {
            throw new ServiceException("物资ID不能为空");
        }
        if (ObjectUtils.isEmpty(stockWarning.getReservePointId())) {
            throw new ServiceException("储备点ID不能为空");
        }
        if (ObjectUtils.isEmpty(stockWarning.getCurrentStock()) || stockWarning.getCurrentStock() < 0) {
            throw new ServiceException("当前库存不能为负数");
        }
        if (ObjectUtils.isEmpty(stockWarning.getWarningThreshold()) || stockWarning.getWarningThreshold() <= 0) {
            throw new ServiceException("预警阈值必须大于0");
        }
        if (ObjectUtils.isEmpty(stockWarning.getNotifyDept())) {
            throw new ServiceException("通知部门不能为空");
        }
        // 校验物资是否存在
        Material material = materialMapper.queryById(stockWarning.getMaterialId());
        if (ObjectUtils.isEmpty(material)) {
            throw new ServiceException("关联物资不存在");
        }
        // 自动判断预警状态
        Integer currentStock = stockWarning.getCurrentStock();
        Integer threshold = stockWarning.getWarningThreshold();
        if (currentStock >= threshold) {
            stockWarning.setWarningStatus(0); // 正常
        } else if (currentStock >= threshold / 2) {
            stockWarning.setWarningStatus(1); // 预警
        } else {
            stockWarning.setWarningStatus(2); // 短缺
        }
        // 默认处置状态为未处置
        stockWarning.setHandleStatus(0);
        stockWarningMapper.saveStockWarning(stockWarning);
    }

    @Override
    public void markAsHandled(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("预警ID不能为空");
        }
        // 校验预警是否存在
        StockWarning warning = stockWarningMapper.queryById(id);
        if (ObjectUtils.isEmpty(warning)) {
            throw new ServiceException("未查询到ID为" + id + "的预警");
        }
        // 校验是否已处置
        if (warning.getHandleStatus().equals(2)) {
            throw new ServiceException("该预警已处置，无需重复操作");
        }
        stockWarningMapper.updateHandleStatus(id, 2);
    }

    @Override
    public void deleteStockWarningById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("预警ID不能为空");
        }
        StockWarning warning = stockWarningMapper.queryById(id);
        if (ObjectUtils.isEmpty(warning)) {
            throw new ServiceException("未查询到ID为" + id + "的预警");
        }
        stockWarningMapper.deleteStockWarning(id);
    }
}