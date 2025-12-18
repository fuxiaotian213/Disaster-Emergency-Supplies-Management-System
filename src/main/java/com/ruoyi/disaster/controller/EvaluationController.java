package com.ruoyi.disaster.controller;

import com.ruoyi.disaster.domain.Evaluation;
import com.ruoyi.disaster.service.EvaluationService;
import com.ruoyi.disaster.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.disaster.util.Anonymous;
import java.util.List;
import java.util.Map;
/**
 * 评价反馈Controller
 */
@RestController
@RequestMapping("/evaluation")
public class  EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    // 按订单ID查询评价
    @Anonymous
    @RequestMapping(value = "/getEvaluationByOrderId", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getEvaluationByOrderId(Long orderId) {
        Evaluation evaluation = evaluationService.getEvaluationByOrderId(orderId);
        return AjaxResult.success(evaluation);
    }

    // 按用户ID查询评价列表
    @Anonymous
    @RequestMapping(value = "/getEvaluationByUserId", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getEvaluationByUserId(Long userId) {
        List<Evaluation> evaluationList = evaluationService.getEvaluationByUserId(userId);
        return AjaxResult.success(evaluationList);
    }

    // 按政府反馈状态查询评价
    @Anonymous
    @RequestMapping(value = "/getEvaluationByGovFeedbackStatus", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getEvaluationByGovFeedbackStatus(Integer isGovFeedback) {
        List<Evaluation> evaluationList = evaluationService.getEvaluationByGovFeedbackStatus(isGovFeedback);
        return AjaxResult.success(evaluationList);
    }

    // 新增评价
    @Anonymous
    @RequestMapping(value = "/addEvaluation", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult addEvaluation(@RequestBody Evaluation evaluation) {
        evaluationService.addEvaluation(evaluation);
        return AjaxResult.success("评价提交成功");
    }

    // 政府回复评价
    @Anonymous
    @RequestMapping(value = "/replyEvaluation", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult replyEvaluation(Long id) {
        evaluationService.replyEvaluation(id);
        return AjaxResult.success("评价回复成功");
    }

    // 删除评价
    @Anonymous
    @RequestMapping(value = "/deleteEvaluation", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public AjaxResult deleteEvaluation(Long id) {
        evaluationService.deleteEvaluationById(id);
        return AjaxResult.success("评价删除成功");
    }

    @Anonymous
    @RequestMapping(value = "/getFeedbackStatistics", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getFeedbackStatistics() {
        Map<String, Integer> stats = evaluationService.getFeedbackStatistics();
        return AjaxResult.success(stats);
    }

    /**
     * 2. 获取反馈列表（支持筛选）
     * @param userName 反馈用户名
     * @param feedbackType 反馈类型（1-4）
     * @param status 处理状态（0-待处理/1-已处理）
     */
    @Anonymous
    @RequestMapping(value = "/getAllFeedback", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getAllFeedback(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) Integer feedbackType,
            @RequestParam(required = false) Integer status
    ) {
        List<Evaluation> feedbackList = evaluationService.getAllFeedback(userName, feedbackType, status);
        return AjaxResult.success(feedbackList);
    }

    /**
     * 3. 按ID获取反馈详情（含用户联系方式）
     */
    @Anonymous
    @RequestMapping(value = "/getFeedbackById", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getFeedbackById(Long id) {
        Evaluation feedback = evaluationService.getFeedbackById(id);
        return AjaxResult.success(feedback);
    }

    /**
     * 4. 处理反馈（标记已处理+添加回复）
     * 请求体示例：{"id":1,"status":1,"reply":"已收到反馈，将尽快优化"}
     */
    @Anonymous
    @RequestMapping(value = "/handleFeedback", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult handleFeedback(@RequestBody Map<String, Object> params) {
        Long id = Long.parseLong(params.get("id").toString());
        Integer status = Integer.parseInt(params.get("status").toString());
        String reply = params.get("reply").toString();
        evaluationService.handleFeedback(id, status, reply);
        return AjaxResult.success("反馈处理成功");
    }

    /**
     * 5. 批量处理反馈
     * 请求体示例：{"status":1,"reply":"您的反馈已收到，我们会尽快处理，感谢您的支持！"}
     */
    @Anonymous
    @RequestMapping(value = "/batchHandleFeedback", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult batchHandleFeedback(@RequestBody Map<String, String> params) {
        Integer status = Integer.parseInt(params.get("status"));
        String reply = params.get("reply");
        int count = evaluationService.batchHandleFeedback(status, reply);
        return AjaxResult.success("批量处理成功", count);
    }
}