package com.ruoyi.disaster.service.Impl;

import com.ruoyi.disaster.domain.Order;
import com.ruoyi.disaster.mapper.OrderMapper;
import com.ruoyi.disaster.service.OrderService;
import com.ruoyi.disaster.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 订单Service实现类
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    // 生成唯一订单号（规则：日期+6位随机数）
    private String generateOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(new Date());
        Random random = new Random();
        String randomStr = String.format("%06d", random.nextInt(1000000));
        return dateStr + randomStr;
    }
    @Override
    public void saveBatch(List<Order> orderList) {
        // 批量保存前：补充后端必要字段（前端未传递的字段）
        for (Order order : orderList) {
            // 1. 生成唯一订单编号
            order.setOrderNo(generateOrderNo());

            // 2. 自动填充申请时间
            order.setCreateTime(new Date());

            // 3. 确保订单状态为 0（待审核，前端传递的可能无效，后端强制设置）
            order.setOrderStatus(0);
        }
    }
    @Override
    public List<Order> getAllOrder(Order order) {
        return orderMapper.queryAll(order);
    }
    @Override
    public boolean updateOrder(Order order) {
        if (order.getId() == null) {
            throw new IllegalArgumentException("订单ID不能为空");
        }
        // 补充更新时间
        order.setUpdateTime(new Date());
        int rows = orderMapper.updateOrder(order);
        return rows > 0;
    }


    @Override
    public Order getOrderById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("订单ID不能为空");
        }
        Order order = orderMapper.queryById(id);
        if (ObjectUtils.isEmpty(order)) {
            throw new ServiceException("未查询到ID为" + id + "的订单");
        }
        return order;
    }

    @Override
    public List<Order> getOrderByUserId(Long userId) {
        if (ObjectUtils.isEmpty(userId)) {
            throw new ServiceException("用户ID不能为空");
        }
        return orderMapper.queryByUserId(userId);
    }

    @Override
    public List<Order> getOrderByOrderStatus(Integer orderStatus) {
        if (ObjectUtils.isEmpty(orderStatus)) {
            throw new ServiceException("订单状态不能为空");
        }
        return orderMapper.queryByOrderStatus(orderStatus);
    }

    @Override
    public List<Order> getOrderByDisasterId(Long disasterId) {
        if (ObjectUtils.isEmpty(disasterId)) {
            throw new ServiceException("灾情ID不能为空");
        }
        return orderMapper.queryByDisasterId(disasterId);
    }

    @Override
    public void addOrder(Order order) {
        // 参数校验
        if (ObjectUtils.isEmpty(order.getUserId())) {
            throw new ServiceException("申请人ID不能为空");
        }
        if (ObjectUtils.isEmpty(order.getDisasterId())) {
            throw new ServiceException("灾情ID不能为空");
        }
        if (ObjectUtils.isEmpty(order.getMaterialId())) {
            throw new ServiceException("物资ID不能为空");
        }
        if (ObjectUtils.isEmpty(order.getApplyNum()) || order.getApplyNum() <= 0) {
            throw new ServiceException("申请数量必须大于0");
        }
        if (ObjectUtils.isEmpty(order.getTargetAddress())) {
            throw new ServiceException("接收地址不能为空");
        }
        // 生成订单号
        order.setOrderNo(generateOrderNo());
        // 默认订单状态为待审核
        order.setOrderStatus(0);
        // 设置申请时间
        order.setCreateTime(new Date());
        // 设置更新时间
        order.setUpdateTime(new Date());
        orderMapper.saveOrder(order);
    }

    @Override
    public void auditOrder(Long id, Integer orderStatus, Long auditUserId, String auditDept) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("订单ID不能为空");
        }
        if (ObjectUtils.isEmpty(orderStatus)) {
            throw new ServiceException("审核状态不能为空");
        }
        if (ObjectUtils.isEmpty(auditUserId)) {
            throw new ServiceException("审核人ID不能为空");
        }
        if (ObjectUtils.isEmpty(auditDept)) {
            throw new ServiceException("审核部门不能为空");
        }
        // 校验订单是否存在
        getOrderById(id);
        orderMapper.auditOrder(id, orderStatus, auditUserId, auditDept);
    }

    @Override
    public void generateDispatch(Long id, String transportTeam) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("订单ID不能为空");
        }
        if (ObjectUtils.isEmpty(transportTeam)) {
            throw new ServiceException("运输团队不能为空");
        }
        // 校验订单是否存在
        Order order = getOrderById(id);
        // 校验订单状态（必须是审核通过）
        if (!order.getOrderStatus().equals(1)) {
            throw new ServiceException("只有审核通过的订单才能生成调度单");
        }
        orderMapper.generateDispatch(id, transportTeam);
    }

    @Override
    public void signOrder(Long id, String signUser, String signTime, Integer actualIssueNum) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("订单ID不能为空");
        }
        if (ObjectUtils.isEmpty(signUser)) {
            throw new ServiceException("签收人不能为空");
        }
        if (ObjectUtils.isEmpty(signTime)) {
            throw new ServiceException("签收时间不能为空");
        }
        if (ObjectUtils.isEmpty(actualIssueNum) || actualIssueNum <= 0) {
            throw new ServiceException("实际发放数量必须大于0");
        }
        // 校验订单是否存在
        Order order = getOrderById(id);
        // 校验订单状态（必须是调度中）
        if (!order.getOrderStatus().equals(2)) {
            throw new ServiceException("只有调度中的订单才能签收");
        }
        // 校验实际发放数量不能超过申请数量
        if (actualIssueNum > order.getApplyNum()) {
            throw new ServiceException("实际发放数量不能超过申请数量");
        }
        orderMapper.signOrder(id, signUser, signTime, actualIssueNum);
    }

    @Override
    public void cancelOrder(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("订单ID不能为空");
        }
        // 校验订单是否存在
        Order order = getOrderById(id);
        // 校验订单状态（只能取消待审核/审核通过的订单）
        if (order.getOrderStatus().equals(3) || order.getOrderStatus().equals(4)) {
            throw new ServiceException("已签收/已取消的订单不能再次取消");
        }
        orderMapper.cancelOrder(id);
    }

    @Override
    public void deleteOrderById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("订单ID不能为空");
        }
        getOrderById(id);
        orderMapper.deleteOrder(id);
    }
}