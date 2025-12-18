package com.ruoyi.disaster.mapper;

import com.ruoyi.disaster.domain.Order;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 订单Mapper接口
 */
public interface OrderMapper {
    // 1. 按ID查询订单
    Order queryById(Long id);


    List<Order> queryAll(@Param("order") Order order);
    int updateOrder(Order order);



    // 2. 按用户ID查询订单列表
    List<Order> queryByUserId(Long userId);

    // 3. 按订单状态查询订单列表
    List<Order> queryByOrderStatus(Integer orderStatus);

    // 4. 按灾情ID查询订单列表
    List<Order> queryByDisasterId(Long disasterId);

    // 5. 新增订单
    void saveOrder(Order order);

    // 6. 审核订单（更新状态、审核人、审核部门）
    void auditOrder(@Param("id") Long id, @Param("orderStatus") Integer orderStatus,
                    @Param("auditUserId") Long auditUserId, @Param("auditDept") String auditDept);

    // 7. 生成调度单（更新运输团队）
    void generateDispatch(@Param("id") Long id, @Param("transportTeam") String transportTeam);

    // 8. 订单签收（更新签收人、签收时间、订单状态）
    void signOrder(@Param("id") Long id, @Param("signUser") String signUser,
                   @Param("signTime") String signTime, @Param("actualIssueNum") Integer actualIssueNum);

    // 9. 取消订单（更新订单状态）
    void cancelOrder(Long id);

    // 10. 删除订单
    void deleteOrder(Long id);
}