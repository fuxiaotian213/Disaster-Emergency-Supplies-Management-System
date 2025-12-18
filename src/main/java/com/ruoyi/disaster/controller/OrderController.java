package com.ruoyi.disaster.controller;

import com.ruoyi.disaster.domain.Order;
import com.ruoyi.disaster.service.OrderService;
import com.ruoyi.disaster.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.disaster.util.Anonymous;
import java.util.List;

/**
 * 订单Controller
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Anonymous
    @RequestMapping(value = "/getAllOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getAllOrder(
            @RequestParam(required = false) String orderNo,
            @RequestParam(required = false) Integer orderStatus){
        // 封装筛选条件
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setOrderStatus(orderStatus);
        // 调用Service获取数据
        List<Order> orderList = orderService.getAllOrder(order);
        return AjaxResult.success(orderList);
    }

    @Anonymous
    @RequestMapping(value = "/updateOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult updateOrder(@RequestBody Order order) {
        try {
            boolean success = orderService.updateOrder(order);
            return success ? AjaxResult.success("订单更新成功") : AjaxResult.error("订单更新失败");
        } catch (IllegalArgumentException e) {
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            return AjaxResult.error("订单更新失败：" + e.getMessage());
        }
    }

    @Anonymous
    @RequestMapping(value = "/addOrderBatch", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult addOrderBatch(@RequestBody List<Order> orderList) {
        orderService.saveBatch(orderList); // 批量保存订单
        return AjaxResult.success("批量提交成功");
    }


    // 按ID查询订单
    @Anonymous
    @RequestMapping(value = "/getOrderById", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getOrderById(Long id) {
        Order order = orderService.getOrderById(id);
        return AjaxResult.success(order);
    }

    // 按用户ID查询订单列表
    @Anonymous
    @RequestMapping(value = "/getOrderByUserId", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getOrderByUserId(Long userId) {
        List<Order> orderList = orderService.getOrderByUserId(userId);
        return AjaxResult.success(orderList);
    }

    // 按订单状态查询订单列表
    @Anonymous
    @RequestMapping(value = "/getOrderByOrderStatus", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getOrderByOrderStatus(Integer orderStatus) {
        List<Order> orderList = orderService.getOrderByOrderStatus(orderStatus);
        return AjaxResult.success(orderList);
    }

    // 按灾情ID查询订单列表
    @Anonymous
    @RequestMapping(value = "/getOrderByDisasterId", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getOrderByDisasterId(Long disasterId) {
        List<Order> orderList = orderService.getOrderByDisasterId(disasterId);
        return AjaxResult.success(orderList);
    }

    // 新增订单 - 支持批量订单（前端发送JSON数组）
    @Anonymous
    @RequestMapping(value = "/addOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult addOrder(@RequestBody List<Order> orderList) {
        if (orderList == null || orderList.isEmpty()) {
            return AjaxResult.error("订单列表不能为空");
        }
        for (Order order : orderList) {
            if (order.getDisasterId() == null) {
                return AjaxResult.error("灾情ID不能为空");
            }
            orderService.addOrder(order);
        }
        return AjaxResult.success("申请提交成功");
    }

    // 审核订单
    @Anonymous
    @RequestMapping(value = "/auditOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult auditOrder(Long id, Integer orderStatus, Long auditUserId, String auditDept) {
        String statusDesc = orderStatus.equals(1) ? "通过" : "驳回";
        orderService.auditOrder(id, orderStatus, auditUserId, auditDept);
        return AjaxResult.success("订单审核" + statusDesc + "成功");
    }

    // 生成调度单
    @Anonymous
    @RequestMapping(value = "/generateDispatch", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult generateDispatch(Long id, String transportTeam) {
        orderService.generateDispatch(id, transportTeam);
        return AjaxResult.success("调度单生成成功，订单状态更新为调度中");
    }

    // 订单签收
    @Anonymous
    @RequestMapping(value = "/signOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult signOrder(Long id, String signUser, String signTime, Integer actualIssueNum) {
        orderService.signOrder(id, signUser, signTime, actualIssueNum);
        return AjaxResult.success("订单签收成功");
    }

    // 取消订单
    @Anonymous
    @RequestMapping(value = "/cancelOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult cancelOrder(Long id) {
        orderService.cancelOrder(id);
        return AjaxResult.success("订单取消成功");
    }

    // 删除订单
    @Anonymous
    @RequestMapping(value = "/deleteOrder", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public AjaxResult deleteOrder(Long id) {
        orderService.deleteOrderById(id);
        return AjaxResult.success("订单删除成功");
    }
}