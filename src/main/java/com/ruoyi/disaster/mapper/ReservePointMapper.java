package com.ruoyi.disaster.mapper;

import com.ruoyi.disaster.domain.ReservePoint;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 储备点Mapper接口
 */
public interface ReservePointMapper {
    // 按ID查询储备点
    ReservePoint queryById(Long id);
    // 按省市查询储备点
    List<ReservePoint> queryByProvinceCity(@Param("province") String province, @Param("city") String city);
    // 查询所有储备点
    List<ReservePoint> queryAll();
    // 新增储备点
    void saveReservePoint(ReservePoint reservePoint);
    // 修改储备点
    void updateReservePoint(ReservePoint reservePoint);
    // 删除储备点
    void deleteReservePoint(Long id);


    List<ReservePoint> queryByIds(List<Long> ids);
}