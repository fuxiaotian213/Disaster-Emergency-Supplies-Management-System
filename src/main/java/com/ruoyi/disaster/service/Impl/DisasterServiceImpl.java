package com.ruoyi.disaster.service.Impl;

import com.ruoyi.disaster.domain.Disaster;
import com.ruoyi.disaster.mapper.DisasterMapper;
import com.ruoyi.disaster.service.DisasterService;
import com.ruoyi.disaster.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 灾情Service实现类
 */
@Service
public class DisasterServiceImpl implements DisasterService{

    @Autowired
    private DisasterMapper disasterMapper;

    @Override
    public List<Disaster> getAllDisaster(Disaster disaster) {
        // 调用Mapper查询，传递筛选条件
        return disasterMapper.queryAll(disaster);
    }

    @Override
    public Disaster getDisasterById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("灾情ID不能为空");
        }
        Disaster disaster = disasterMapper.queryById(id);
        if (ObjectUtils.isEmpty(disaster)) {
            throw new ServiceException("未查询到ID为" + id + "的灾情");
        }
        return disaster;
    }

    @Override
    public List<Disaster> getDisasterByIds(List<Long> ids) {
        if (ObjectUtils.isEmpty(ids) || ids.isEmpty()) {
            throw new ServiceException("灾情ID列表不能为空");
        }
        return disasterMapper.queryByIds(ids);
    }

    @Override
    public List<Disaster> getDisasterByReportUserId(Long reportUserId) {
        if (ObjectUtils.isEmpty(reportUserId)) {
            throw new ServiceException("上报人ID不能为空");
        }
        return disasterMapper.queryByReportUserId(reportUserId);
    }

    @Override
    public List<Disaster> getDisasterByApproveStatus(Integer approveStatus) {
        if (ObjectUtils.isEmpty(approveStatus)) {
            throw new ServiceException("审核状态不能为空");
        }
        return disasterMapper.queryByApproveStatus(approveStatus);
    }

    @Override
    public List<Disaster> getDisasterByCondition(Integer disasterLevel, String disasterArea) {
        return disasterMapper.queryByCondition(disasterLevel, disasterArea);
    }

    @Override
    public void addDisaster(Disaster disaster) {
        // 参数校验
        if (ObjectUtils.isEmpty(disaster.getDisasterName())) {
            throw new ServiceException("灾情名称不能为空");
        }
        if (ObjectUtils.isEmpty(disaster.getDisasterLevel())) {
            throw new ServiceException("灾情分级不能为空");
        }
        if (ObjectUtils.isEmpty(disaster.getDisasterArea())) {
            throw new ServiceException("受灾区域不能为空");
        }
        if (ObjectUtils.isEmpty(disaster.getReportUserId())) {
            throw new ServiceException("上报人ID不能为空");
        }
        // 默认审核状态为待审核
        disaster.setApproveStatus(0);
        disasterMapper.saveDisaster(disaster);
    }

    @Override
    public void auditDisaster(Disaster disaster) {
        // 1. 校验必填参数（避免空指针）
        if (disaster.getId() == null) {
            throw new IllegalArgumentException("灾情ID不能为空");
        }
        if (disaster.getApproveStatus() == null || !(disaster.getApproveStatus() == 1 || disaster.getApproveStatus() == 2)) {
            throw new IllegalArgumentException("审核状态无效（仅支持1=通过/2=驳回）");
        }
        if (disaster.getApproveUserId() == null) {
            throw new IllegalArgumentException("审核人ID不能为空");
        }
        if (disaster.getApproveDept() == null || disaster.getApproveDept().trim().isEmpty()) {
            throw new IllegalArgumentException("审核部门不能为空");
        }
        // 2. 校验灾情是否存在
        Disaster dbDisaster = disasterMapper.queryById(disaster.getId());
        if (dbDisaster == null) {
            throw new IllegalArgumentException("灾情不存在或已删除");
        }
        // 3. 填充审核信息到数据库实体（避免覆盖原有字段）
        dbDisaster.setApproveStatus(disaster.getApproveStatus());
        dbDisaster.setApproveUserId(disaster.getApproveUserId());
        dbDisaster.setApproveDept(disaster.getApproveDept());// 备注可为空
        // 4. 执行更新
        disasterMapper.updateAudit(dbDisaster);
    }

    @Override
    public void deleteDisasterById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("灾情ID不能为空");
        }
        getDisasterById(id);
        disasterMapper.deleteDisaster(id);
    }
}