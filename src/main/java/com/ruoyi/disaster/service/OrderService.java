package com.ruoyi.disaster.service;

import com.ruoyi.disaster.domain.Order;
import java.util.List;

/**
 * 订单Service接口
 */
public interface OrderService {

    List<Order> getAllOrder(Order order);
    boolean updateOrder(Order order);


    // 按ID查询订单
    Order getOrderById(Long id);

    // 按用户ID查询订单列表
    List<Order> getOrderByUserId(Long userId);

    // 按订单状态查询订单列表
    List<Order> getOrderByOrderStatus(Integer orderStatus);

    // 按灾情ID查询订单列表
    List<Order> getOrderByDisasterId(Long disasterId);

    // 新增订单（生成唯一订单号）
    void addOrder(Order order);

    // 审核订单
    void auditOrder(Long id, Integer orderStatus, Long auditUserId, String auditDept);

    // 生成调度单
    void generateDispatch(Long id, String transportTeam);

    // 订单签收
    void signOrder(Long id, String signUser, String signTime, Integer actualIssueNum);

    // 取消订单
    void cancelOrder(Long id);

    // 删除订单
    void deleteOrderById(Long id);

    void saveBatch(List<Order> orderList);
}