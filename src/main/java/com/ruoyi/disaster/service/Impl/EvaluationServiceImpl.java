package com.ruoyi.disaster.service.Impl;

import com.ruoyi.disaster.domain.Evaluation;
import com.ruoyi.disaster.domain.Order;
import com.ruoyi.disaster.mapper.EvaluationMapper;
import com.ruoyi.disaster.mapper.OrderMapper;
import com.ruoyi.disaster.service.EvaluationService;
import com.ruoyi.disaster.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

import java.util.Map;
/**
 * 评价反馈Service实现类
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Evaluation getEvaluationByOrderId(Long orderId) {
        if (ObjectUtils.isEmpty(orderId)) {
            throw new ServiceException("订单ID不能为空");
        }
        return evaluationMapper.queryByOrderId(orderId);
    }

    @Override
    public List<Evaluation> getEvaluationByUserId(Long userId) {
        if (ObjectUtils.isEmpty(userId)) {
            throw new ServiceException("用户ID不能为空");
        }
        return evaluationMapper.queryByUserId(userId);
    }

    @Override
    public List<Evaluation> getEvaluationByGovFeedbackStatus(Integer isGovFeedback) {
        if (ObjectUtils.isEmpty(isGovFeedback)) {
            throw new ServiceException("反馈状态不能为空");
        }
        return evaluationMapper.queryByGovFeedbackStatus(isGovFeedback);
    }

    @Override
    public void addEvaluation(Evaluation evaluation) {
        // 参数校验
        if (ObjectUtils.isEmpty(evaluation.getOrderId())) {
            throw new ServiceException("订单ID不能为空");
        }
        if (ObjectUtils.isEmpty(evaluation.getUserId())) {
            throw new ServiceException("评价人ID不能为空");
        }
        if (ObjectUtils.isEmpty(evaluation.getMaterialAdapt()) || evaluation.getMaterialAdapt() < 1 || evaluation.getMaterialAdapt() > 5) {
            throw new ServiceException("物资适配度必须为1-5分");
        }
        if (ObjectUtils.isEmpty(evaluation.getDispatchEfficiency()) || evaluation.getDispatchEfficiency() < 1 || evaluation.getDispatchEfficiency() > 5) {
            throw new ServiceException("调度效率必须为1-5分");
        }
        // 校验订单是否存在且已签收
        Order order = orderMapper.queryById(evaluation.getOrderId());
        if (ObjectUtils.isEmpty(order)) {
            throw new ServiceException("关联订单不存在");
        }
        if (!order.getOrderStatus().equals(3)) {
            throw new ServiceException("只有已签收的订单才能评价");
        }
        // 校验该订单是否已评价
        Evaluation existEval = evaluationMapper.queryByOrderId(evaluation.getOrderId());
        if (!ObjectUtils.isEmpty(existEval)) {
            throw new ServiceException("该订单已评价，无需重复提交");
        }
        // 默认政府未反馈
        evaluation.setIsGovFeedback(0);
        evaluationMapper.saveEvaluation(evaluation);
    }

    @Override
    public void replyEvaluation(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("评价ID不能为空");
        }
        // 校验评价是否存在
        Evaluation evaluation = evaluationMapper.queryByOrderId(id);
        if (ObjectUtils.isEmpty(evaluation)) {
            throw new ServiceException("未查询到ID为" + id + "的评价");
        }
        // 校验是否已反馈
        if (evaluation.getIsGovFeedback().equals(1)) {
            throw new ServiceException("该评价已反馈，无需重复操作");
        }
        evaluationMapper.replyEvaluation(id, 1);
    }

    @Override
    public void deleteEvaluationById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("评价ID不能为空");
        }
        Evaluation evaluation = evaluationMapper.queryByOrderId(id);
        if (ObjectUtils.isEmpty(evaluation)) {
            throw new ServiceException("未查询到ID为" + id + "的评价");
        }
        evaluationMapper.deleteEvaluation(id);
    }

    @Override
    public Map<String, Integer> getFeedbackStatistics() {
        Map<String, Integer> stats = evaluationMapper.queryFeedbackStatistics();
        // 兜底：若统计结果为空，返回默认0
        stats.putIfAbsent("total", 0);
        stats.putIfAbsent("pending", 0);
        stats.putIfAbsent("handled", 0);
        return stats;
    }

    @Override
    public List<Evaluation> getAllFeedback(String userName, Integer feedbackType, Integer status) {
        return evaluationMapper.queryAllFeedback(userName, feedbackType, status);
    }

    @Override
    public Evaluation getFeedbackById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("反馈ID不能为空");
        }
        Evaluation evaluation = evaluationMapper.queryFeedbackById(id);
        if (ObjectUtils.isEmpty(evaluation)) {
            throw new ServiceException("未查询到ID为" + id + "的反馈");
        }
        return evaluation;
    }

    @Override
    public void handleFeedback(Long id, Integer status, String reply) {
        // 参数校验
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("反馈ID不能为空");
        }
        if (ObjectUtils.isEmpty(status) || !status.equals(1)) {
            throw new ServiceException("处理状态必须为「已处理（1）」");
        }
        if (ObjectUtils.isEmpty(reply) || reply.trim().isEmpty()) {
            throw new ServiceException("处理回复不能为空");
        }
        // 校验反馈存在且未处理
        Evaluation evaluation = evaluationMapper.queryFeedbackById(id);
        if (ObjectUtils.isEmpty(evaluation)) {
            throw new ServiceException("未查询到ID为" + id + "的反馈");
        }
        if (evaluation.getStatus().equals(1)) {
            throw new ServiceException("该反馈已处理，无需重复操作");
        }
        // 执行更新
        evaluationMapper.updateFeedbackHandle(id, status, reply.trim());
    }

    @Override
    public int batchHandleFeedback(Integer status, String reply) {
        // 参数校验
        if (ObjectUtils.isEmpty(status) || !status.equals(1)) {
            throw new ServiceException("批量处理目标状态必须为「已处理（1）」");
        }
        if (ObjectUtils.isEmpty(reply) || reply.trim().isEmpty()) {
            throw new ServiceException("统一回复内容不能为空");
        }
        // 执行批量更新（仅更新待处理状态的反馈）
        return evaluationMapper.batchUpdateFeedbackHandle(status, reply.trim());
    }
}