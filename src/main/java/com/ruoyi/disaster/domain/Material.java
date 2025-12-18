package com.ruoyi.disaster.domain;

import java.util.Date;

/**
 * 救援物资实体类（对应数据库 biz_material 表）
 */
public class Material {
    /** 物资唯一标识 */
    private Long id;
    /** 物资名称 */
    private String materialName;
    /** 物资类型（1-帐篷/2-食品/3-药品/4-工具） */
    private Integer materialType;
    /** 规格 */
    private String spec;
    /** 当前库存数量 */
    private Integer stockNum;
    /** 关联储备点ID */
    private Long reservePointId;
    /** 保质期（药品/食品必填） */
    private Date qualityPeriod;
    /** 物资优先级（1-高/2-中/3-低） */
    private Integer priorityLevel;
    /** 存储条件 */
    private String storageCondition;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

    // 无参构造（MyBatis反射必需）
    public Material() {}

    // 全参构造
    public Material(Long id, String materialName, Integer materialType, String spec, Integer stockNum,
                    Long reservePointId, Date qualityPeriod, Integer priorityLevel, String storageCondition,
                    Date createTime, Date updateTime) {
        this.id = id;
        this.materialName = materialName;
        this.materialType = materialType;
        this.spec = spec;
        this.stockNum = stockNum;
        this.reservePointId = reservePointId;
        this.qualityPeriod = qualityPeriod;
        this.priorityLevel = priorityLevel;
        this.storageCondition = storageCondition;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // 所有字段的getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMaterialName() { return materialName; }
    public void setMaterialName(String materialName) { this.materialName = materialName; }
    public Integer getMaterialType() { return materialType; }
    public void setMaterialType(Integer materialType) { this.materialType = materialType; }
    public String getSpec() { return spec; }
    public void setSpec(String spec) { this.spec = spec; }
    public Integer getStockNum() { return stockNum; }
    public void setStockNum(Integer stockNum) { this.stockNum = stockNum; }
    public Long getReservePointId() { return reservePointId; }
    public void setReservePointId(Long reservePointId) { this.reservePointId = reservePointId; }
    public Date getQualityPeriod() { return qualityPeriod; }
    public void setQualityPeriod(Date qualityPeriod) { this.qualityPeriod = qualityPeriod; }
    public Integer getPriorityLevel() { return priorityLevel; }
    public void setPriorityLevel(Integer priorityLevel) { this.priorityLevel = priorityLevel; }
    public String getStorageCondition() { return storageCondition; }
    public void setStorageCondition(String storageCondition) { this.storageCondition = storageCondition; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", materialName='" + materialName + '\'' +
                ", materialType=" + materialType +
                ", spec='" + spec + '\'' +
                ", stockNum=" + stockNum +
                ", reservePointId=" + reservePointId +
                ", qualityPeriod=" + qualityPeriod +
                ", priorityLevel=" + priorityLevel +
                ", storageCondition='" + storageCondition + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}