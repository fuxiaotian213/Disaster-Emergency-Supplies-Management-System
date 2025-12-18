package com.ruoyi.disaster.controller;
import com.ruoyi.disaster.util.Anonymous;
import com.ruoyi.disaster.domain.Disaster;
import com.ruoyi.disaster.service.DisasterService;

import com.ruoyi.disaster.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 灾情Controller
 */
@RestController
@RequestMapping("/disaster")
public class DisasterController {

    @Autowired
    private DisasterService disasterService;


    @Anonymous // 允许匿名访问（参考ReservePointController）
    @RequestMapping(value = "/getAllDisaster", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getAllDisaster(
            @RequestParam(required = false) String disasterName,
            @RequestParam(required = false) Integer disasterLevel,
            @RequestParam(required = false) Integer approveStatus) {

        // 封装筛选条件到实体类
        Disaster disaster = new Disaster();
        disaster.setDisasterName(disasterName);
        disaster.setDisasterLevel(disasterLevel);
        disaster.setApproveStatus(approveStatus);

        // 调用Service获取数据
        List<Disaster> disasterList = disasterService.getAllDisaster(disaster);

        // 返回成功结果（与前端AjaxResult格式对齐）
        return AjaxResult.success(disasterList);
    }


    // 按ID查询灾情
    @Anonymous
    @RequestMapping(value = "/getDisasterById", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getDisasterById(Long id) {
        Disaster disaster = disasterService.getDisasterById(id);
        return AjaxResult.success(disaster);
    }
    
    // 按ID列表批量查询灾情
    @Anonymous
    @RequestMapping(value = "/getDisasterByIds", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getDisasterByIds(@RequestParam("ids") List<Long> ids) {
        List<Disaster> disasterList = disasterService.getDisasterByIds(ids);
        return AjaxResult.success(disasterList);
    }

    // 按上报人ID查询灾情列表
    @Anonymous
    @RequestMapping(value = "/getDisasterByReportUserId", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getDisasterByReportUserId(Long reportUserId) {
        List<Disaster> disasterList = disasterService.getDisasterByReportUserId(reportUserId);
        return AjaxResult.success(disasterList);
    }

    // 按审核状态查询灾情列表
    @Anonymous
    @RequestMapping(value = "/getDisasterByApproveStatus", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getDisasterByApproveStatus(Integer approveStatus) {
        List<Disaster> disasterList = disasterService.getDisasterByApproveStatus(approveStatus);
        return AjaxResult.success(disasterList);
    }

    // 按条件查询灾情
    @Anonymous
    @RequestMapping(value = "/getDisasterByCondition", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getDisasterByCondition(Integer disasterLevel, String disasterArea) {
        List<Disaster> disasterList = disasterService.getDisasterByCondition(disasterLevel, disasterArea);
        return AjaxResult.success(disasterList);
    }

    // 新增灾情
    @Anonymous
    @RequestMapping(value = "/addDisaster", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult addDisaster(@RequestBody Disaster disaster) {
        // 后端二次校验：避免前端未传审核部门
        if (disaster.getApproveDept() == null || disaster.getApproveDept().trim().isEmpty()) {
            return AjaxResult.error("审核部门不能为空");
        }
        disasterService.addDisaster(disaster);
        return AjaxResult.success("灾情新增成功（管理员默认通过）");
    }

    // 审核灾情
    @Anonymous
    @RequestMapping(value = "/auditDisaster", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult auditDisaster(@RequestBody Disaster disaster) {
        try {
            // 调用Service审核（参数校验和业务逻辑在Service层处理）
            disasterService.auditDisaster(disaster);
            String statusDesc = disaster.getApproveStatus().equals(1) ? "通过" : "驳回";
            return AjaxResult.success("灾情审核" + statusDesc + "成功");
        } catch (IllegalArgumentException e) {
            // 捕获参数校验错误，返回明确提示
            return AjaxResult.error("审核失败：" + e.getMessage());
        } catch (Exception e) {
            // 捕获其他异常，避免500模糊错误
            return AjaxResult.error("审核失败：服务器内部错误（" + e.getMessage() + "）");
        }
    }

    // 删除灾情
    @Anonymous
    @RequestMapping(value = "/deleteDisaster", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public AjaxResult deleteDisaster(Long id) {
        disasterService.deleteDisasterById(id);
        return AjaxResult.success("灾情删除成功");
    }
}