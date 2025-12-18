package com.ruoyi.disaster.service;

import com.ruoyi.disaster.domain.Evaluation;
import java.util.List;

import java.util.Map;
/**
 * 评价反馈Service接口
 */
public interface EvaluationService {
    // 按订单ID查询评价
    Evaluation getEvaluationByOrderId(Long orderId);

    // 按用户ID查询评价列表
    List<Evaluation> getEvaluationByUserId(Long userId);

    // 按政府反馈状态查询评价
    List<Evaluation> getEvaluationByGovFeedbackStatus(Integer isGovFeedback);

    // 新增评价
    void addEvaluation(Evaluation evaluation);

    // 政府回复评价
    void replyEvaluation(Long id);

    // 删除评价
    void deleteEvaluationById(Long id);


    Map<String, Integer> getFeedbackStatistics();

    /**
     * 2. 获取反馈列表（支持筛选）
     * @param userName 反馈用户名
     * @param feedbackType 反馈类型（1-4）
     * @param status 处理状态（0-待处理/1-已处理）
     */
    List<Evaluation> getAllFeedback(String userName, Integer feedbackType, Integer status);

    /**
     * 3. 按ID获取反馈详情（含用户联系方式）
     */
    Evaluation getFeedbackById(Long id);

    /**
     * 4. 处理反馈（标记已处理+添加回复）
     * @param id 反馈ID
     * @param status 处理状态（1-已处理）
     * @param reply 处理回复
     */
    void handleFeedback(Long id, Integer status, String reply);

    /**
     * 5. 批量处理反馈（仅处理待处理状态）
     * @param status 目标状态（1-已处理）
     * @param reply 统一回复内容
     * @return 处理成功的反馈数量
     */
    int batchHandleFeedback(Integer status, String reply);
}