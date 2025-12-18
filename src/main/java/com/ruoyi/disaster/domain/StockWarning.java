package com.ruoyi.disaster.domain;

import java.util.Date;

/**
 * 库存预警表实体类（对应数据库 biz_stock_warning 表）
 */
public class StockWarning {
    /** 预警唯一标识 */
    private Long id;
    /** 关联物资ID（关联biz_material表） */
    private Long materialId;
    /** 关联储备点ID（关联biz_reserve_point表） */
    private Long reservePointId;
    /** 当前库存 */
    private Integer currentStock;
    /** 预警阈值（政府设定） */
    private Integer warningThreshold;
    /** 预警状态（0-正常/1-预警/2-短缺） */
    private Integer warningStatus;
    /** 通知部门 */
    private String notifyDept;
    /** 处置状态（0-未处置/1-处置中/2-已处置） */
    private Integer handleStatus;
    /** 预警生成时间 */
    private Date createTime;
    /** 预警更新时间 */
    private Date updateTime;

    // 无参构造
    public StockWarning() {}

    // 全参构造
    public StockWarning(Long id, Long materialId, Long reservePointId, Integer currentStock, Integer warningThreshold,
                        Integer warningStatus, String notifyDept, Integer handleStatus, Date createTime, Date updateTime) {
        this.id = id;
        this.materialId = materialId;
        this.reservePointId = reservePointId;
        this.currentStock = currentStock;
        this.warningThreshold = warningThreshold;
        this.warningStatus = warningStatus;
        this.notifyDept = notifyDept;
        this.handleStatus = handleStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long materialId) { this.materialId = materialId; }
    public Long getReservePointId() { return reservePointId; }
    public void setReservePointId(Long reservePointId) { this.reservePointId = reservePointId; }
    public Integer getCurrentStock() { return currentStock; }
    public void setCurrentStock(Integer currentStock) { this.currentStock = currentStock; }
    public Integer getWarningThreshold() { return warningThreshold; }
    public void setWarningThreshold(Integer warningThreshold) { this.warningThreshold = warningThreshold; }
    public Integer getWarningStatus() { return warningStatus; }
    public void setWarningStatus(Integer warningStatus) { this.warningStatus = warningStatus; }
    public String getNotifyDept() { return notifyDept; }
    public void setNotifyDept(String notifyDept) { this.notifyDept = notifyDept; }
    public Integer getHandleStatus() { return handleStatus; }
    public void setHandleStatus(Integer handleStatus) { this.handleStatus = handleStatus; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

    @Override
    public String toString() {
        return "StockWarning{" +
                "id=" + id +
                ", materialId=" + materialId +
                ", reservePointId=" + reservePointId +
                ", currentStock=" + currentStock +
                ", warningThreshold=" + warningThreshold +
                ", warningStatus=" + warningStatus +
                ", notifyDept='" + notifyDept + '\'' +
                ", handleStatus=" + handleStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}