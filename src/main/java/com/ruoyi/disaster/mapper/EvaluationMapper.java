package com.ruoyi.disaster.mapper;

import com.ruoyi.disaster.domain.Evaluation;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * 评价反馈Mapper接口
 */
public interface EvaluationMapper {
    // 按订单ID查询评价
    Evaluation queryByOrderId(Long orderId);
    // 按用户ID查询评价列表
    List<Evaluation> queryByUserId(Long userId);
    // 按政府反馈状态查询评价
    List<Evaluation> queryByGovFeedbackStatus(Integer isGovFeedback);
    // 新增评价
    void saveEvaluation(Evaluation evaluation);
    // 政府回复评价
    void replyEvaluation(@Param("id") Long id, @Param("isGovFeedback") Integer isGovFeedback);

    void deleteEvaluation(Long id);
    Map<String, Integer> queryFeedbackStatistics();

    /**
     * 2. 加载所有反馈（支持筛选：用户名/反馈类型/处理状态）
     * @param userName 反馈用户名（关联biz_user.real_name）
     * @param feedbackType 反馈类型（1-4）
     * @param status 处理状态（0-待处理/1-已处理）
     */
    List<Evaluation> queryAllFeedback(
            @Param("userName") String userName,
            @Param("feedbackType") Integer feedbackType,
            @Param("status") Integer status
    );

    /**
     * 3. 按ID查询反馈详情（关联用户表获取用户名）
     */
    Evaluation queryFeedbackById(Long id);

    /**
     * 4. 处理反馈（更新状态和回复）
     */
    void updateFeedbackHandle(
            @Param("id") Long id,
            @Param("status") Integer status,
            @Param("reply") String reply
    );

    /**
     * 5. 批量处理反馈（仅更新待处理状态的反馈）
     */
    int batchUpdateFeedbackHandle(
            @Param("status") Integer status,
            @Param("reply") String reply
    );
}