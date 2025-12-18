package com.ruoyi.disaster.domain;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 物资订单表实体类（对应数据库 biz_order 表）
 */
public class Order {
    /** 订单唯一标识 */
    private Long id;
    /** 订单编号 */
    private String orderNo;
    /** 申请人ID（关联user表） */
    private Long userId;
    /** 关联灾情ID（关联biz_disaster表） */
    private Long disasterId;
    /** 关联物资ID（关联biz_material表） */
    private Long materialId;
    /** 申请数量 */
    private Integer applyNum;
    /** 实际发放数量 */
    private Integer actualIssueNum;
    /** 调出储备点ID（关联biz_reserve_point表） */
    private Long reservePointId;
    /** 物资接收地址 */
    private String targetAddress;
    /** 订单状态（0-待政府审核/1-审核通过/2-调度中/3-已签收/4-已取消） */
    private Integer orderStatus;
    /** 审核部门 */
    private String auditDept;
    /** 审核人ID（关联user表） */
    private Long auditUserId;
    /** 运输团队名称 */
    private String transportTeam;
    /** 签收人姓名 */
    private String signUser;
    /** 签收时间 */
    private Date signTime;
    /** 创建时间（申请时间） */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

    // 无参构造
    public Order() {}

    // 全参构造
    public Order(Long id, String orderNo, Long userId, Long disasterId, Long materialId, Integer applyNum,
                 Integer actualIssueNum, Long reservePointId, String targetAddress, Integer orderStatus,
                 String auditDept, Long auditUserId, String transportTeam, String signUser, Date signTime,
                 Date createTime, Date updateTime) {
        this.id = id;
        this.orderNo = orderNo;
        this.userId = userId;
        this.disasterId = disasterId;
        this.materialId = materialId;
        this.applyNum = applyNum;
        this.actualIssueNum = actualIssueNum;
        this.reservePointId = reservePointId;
        this.targetAddress = targetAddress;
        this.orderStatus = orderStatus;
        this.auditDept = auditDept;
        this.auditUserId = auditUserId;
        this.transportTeam = transportTeam;
        this.signUser = signUser;
        this.signTime = signTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getDisasterId() { return disasterId; }
    public void setDisasterId(Long disasterId) { this.disasterId = disasterId; }
    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long materialId) { this.materialId = materialId; }
    public Integer getApplyNum() { return applyNum; }
    public void setApplyNum(Integer applyNum) { this.applyNum = applyNum; }
    public Integer getActualIssueNum() { return actualIssueNum; }
    public void setActualIssueNum(Integer actualIssueNum) { this.actualIssueNum = actualIssueNum; }
    public Long getReservePointId() { return reservePointId; }
    public void setReservePointId(Long reservePointId) { this.reservePointId = reservePointId; }
    public String getTargetAddress() { return targetAddress; }
    public void setTargetAddress(String targetAddress) { this.targetAddress = targetAddress; }
    public Integer getOrderStatus() { return orderStatus; }
    public void setOrderStatus(Integer orderStatus) { this.orderStatus = orderStatus; }
    public String getAuditDept() { return auditDept; }
    public void setAuditDept(String auditDept) { this.auditDept = auditDept; }
    public Long getAuditUserId() { return auditUserId; }
    public void setAuditUserId(Long auditUserId) { this.auditUserId = auditUserId; }
    public String getTransportTeam() { return transportTeam; }
    public void setTransportTeam(String transportTeam) { this.transportTeam = transportTeam; }
    public String getSignUser() { return signUser; }
    public void setSignUser(String signUser) { this.signUser = signUser; }
    public Date getSignTime() { return signTime; }
    public void setSignTime(Date signTime) { this.signTime = signTime; }
    public Date getCreateTime() { return createTime; }

    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", userId=" + userId +
                ", disasterId=" + disasterId +
                ", materialId=" + materialId +
                ", applyNum=" + applyNum +
                ", actualIssueNum=" + actualIssueNum +
                ", reservePointId=" + reservePointId +
                ", targetAddress='" + targetAddress + '\'' +
                ", orderStatus=" + orderStatus +
                ", auditDept='" + auditDept + '\'' +
                ", auditUserId=" + auditUserId +
                ", transportTeam='" + transportTeam + '\'' +
                ", signUser='" + signUser + '\'' +
                ", signTime=" + signTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public void setCreateTime(Date date) {
    }
}