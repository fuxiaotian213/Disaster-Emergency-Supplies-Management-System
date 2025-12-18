package com.ruoyi.disaster.mapper;

import com.ruoyi.disaster.domain.Disaster;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 灾情Mapper接口
 */
public interface DisasterMapper {


    List<Disaster> queryAll(@Param("disaster") Disaster disaster);





    // 1. 按ID查询灾情
    Disaster queryById(Long id);
    
    // 2. 按ID列表批量查询灾情
    List<Disaster> queryByIds(List<Long> ids);

    // 3. 按上报人ID查询灾情列表
    List<Disaster> queryByReportUserId(Long reportUserId);

    // 3. 按审核状态查询灾情列表
    List<Disaster> queryByApproveStatus(Integer approveStatus);

    // 4. 按条件查询灾情（分级+区域）
    List<Disaster> queryByCondition(@Param("disasterLevel") Integer disasterLevel, @Param("disasterArea") String disasterArea);

    // 5. 新增灾情
    void saveDisaster(Disaster disaster);

    // 6. 审核灾情（更新审核状态、审核人、审核部门）
    void auditDisaster(@Param("id") Long id, @Param("approveStatus") Integer approveStatus,
                       @Param("approveUserId") Long approveUserId, @Param("approveDept") String approveDept);

    // 7. 删除灾情
    void deleteDisaster(Long id);

    void updateAudit(Disaster dbDisaster);
}