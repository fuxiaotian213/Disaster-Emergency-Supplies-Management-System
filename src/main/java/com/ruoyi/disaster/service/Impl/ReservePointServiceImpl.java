package com.ruoyi.disaster.service.Impl;

import com.ruoyi.disaster.domain.ReservePoint;
import com.ruoyi.disaster.mapper.ReservePointMapper;
import com.ruoyi.disaster.service.ReservePointService;
import com.ruoyi.disaster.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 物资储备点Service实现类
 */
@Service
public class ReservePointServiceImpl implements ReservePointService {

    @Autowired
    private ReservePointMapper reservePointMapper;

    @Override
    public ReservePoint getReservePointById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("储备点ID不能为空");
        }
        ReservePoint reservePoint = reservePointMapper.queryById(id);
        if (ObjectUtils.isEmpty(reservePoint)) {
            throw new ServiceException("未查询到ID为" + id + "的储备点");
        }
        return reservePoint;
    }

    @Override
    public List<ReservePoint> getReservePointByProvinceCity(String province, String city) {
        if (ObjectUtils.isEmpty(province)) {
            throw new ServiceException("省份不能为空");
        }
        if (ObjectUtils.isEmpty(city)) {
            throw new ServiceException("城市不能为空");
        }
        return reservePointMapper.queryByProvinceCity(province, city);
    }

    @Override
    public List<ReservePoint> getAllReservePoint() {
        return reservePointMapper.queryAll();
    }

    @Override
    public void addReservePoint(ReservePoint reservePoint) {
        // 参数校验
        if (ObjectUtils.isEmpty(reservePoint.getPointName())) {
            throw new ServiceException("储备点名称不能为空");
        }
        if (ObjectUtils.isEmpty(reservePoint.getProvince())) {
            throw new ServiceException("省份不能为空");
        }
        if (ObjectUtils.isEmpty(reservePoint.getCity())) {
            throw new ServiceException("城市不能为空");
        }
        if (ObjectUtils.isEmpty(reservePoint.getAddress())) {
            throw new ServiceException("详细地址不能为空");
        }
        if (ObjectUtils.isEmpty(reservePoint.getContactPerson())) {
            throw new ServiceException("联系人不能为空");
        }
        if (ObjectUtils.isEmpty(reservePoint.getContactPhone())) {
            throw new ServiceException("联系电话不能为空");
        }
        reservePointMapper.saveReservePoint(reservePoint);
    }

    @Override
    public void modifyReservePoint(ReservePoint reservePoint) {
        if (ObjectUtils.isEmpty(reservePoint.getId())) {
            throw new ServiceException("储备点ID不能为空");
        }
        // 校验储备点是否存在
        getReservePointById(reservePoint.getId());
        reservePointMapper.updateReservePoint(reservePoint);
    }

    @Override
    public void deleteReservePointById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("储备点ID不能为空");
        }
        getReservePointById(id);
        reservePointMapper.deleteReservePoint(id);
    }


    @Override
    public List<ReservePoint> getReservePointByIds(List<Long> ids) {
        if (ObjectUtils.isEmpty(ids) || ids.size() == 0) {
            throw new ServiceException("储备点ID列表不能为空");
        }
        return reservePointMapper.queryByIds(ids);
    }
}