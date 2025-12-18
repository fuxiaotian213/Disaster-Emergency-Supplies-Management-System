package com.ruoyi.disaster.service;

import com.ruoyi.disaster.domain.Disaster;
import java.util.List;

/**
 * 灾情Service接口
 */
public interface DisasterService {


    List<Disaster> getAllDisaster(Disaster disaster);



    // 按ID查询灾情
    Disaster getDisasterById(Long id);
    
    // 批量按ID查询灾情
    List<Disaster> getDisasterByIds(List<Long> ids);

    // 按上报人ID查询灾情列表
    List<Disaster> getDisasterByReportUserId(Long reportUserId);

    // 按审核状态查询灾情列表
    List<Disaster> getDisasterByApproveStatus(Integer approveStatus);

    // 按条件查询灾情（分级+区域）
    List<Disaster> getDisasterByCondition(Integer disasterLevel, String disasterArea);

    // 新增灾情
    void addDisaster(Disaster disaster);

    // 审核灾情
    void auditDisaster(Disaster disaster);

    // 删除灾情
    void deleteDisasterById(Long id);
}