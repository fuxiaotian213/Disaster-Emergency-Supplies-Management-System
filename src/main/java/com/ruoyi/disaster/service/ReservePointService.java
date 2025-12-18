package com.ruoyi.disaster.service;

import com.ruoyi.disaster.domain.ReservePoint;
import java.util.List;

/**
 * 物资储备点Service接口
 */
public interface ReservePointService {
    // 按ID查询储备点
    ReservePoint getReservePointById(Long id);

    // 按省市查询储备点
    List<ReservePoint> getReservePointByProvinceCity(String province, String city);

    // 查询所有储备点
    List<ReservePoint> getAllReservePoint();

    // 新增储备点
    void addReservePoint(ReservePoint reservePoint);

    // 修改储备点
    void modifyReservePoint(ReservePoint reservePoint);

    // 删除储备点
    void deleteReservePointById(Long id);
    List<ReservePoint> getReservePointByIds(List<Long> ids);
}