package com.ruoyi.disaster.domain;

import java.util.Date;

/**
 * 灾情信息表实体类（对应数据库 biz_disaster 表）
 */
public class Disaster {
    /** 灾情唯一标识 */
    private Long id;
    /** 灾情名称 */
    private String disasterName;
    /** 灾情分级（1-一级/2-二级/3-三级） */
    private Integer disasterLevel;
    /** 受灾区域 */
    private String disasterArea;
    /** 受灾人数 */
    private Integer affectPeople;
    /** 上报人ID（关联user表） */
    private Long reportUserId;
    /** 审核部门 */
    private String approveDept;
    /** 审核人ID（关联user表） */
    private Long approveUserId;
    /** 审核状态（0-待审核/1-政府已通过/2-已驳回） */
    private Integer approveStatus;
    /** 灾情详细描述 */
    private String description;
    /** 上报时间 */
    private Date createTime;
    private String approveRemark;
    // 无参构造
    public Disaster() {}

    // 全参构造
    public Disaster(Long id, String disasterName, Integer disasterLevel, String disasterArea, Integer affectPeople,
                    Long reportUserId, String approveDept, Long approveUserId, Integer approveStatus,
                    String description, Date createTime) {
        this.id = id;
        this.disasterName = disasterName;
        this.disasterLevel = disasterLevel;
        this.disasterArea = disasterArea;
        this.affectPeople = affectPeople;
        this.reportUserId = reportUserId;
        this.approveDept = approveDept;
        this.approveUserId = approveUserId;
        this.approveStatus = approveStatus;
        this.description = description;
        this.createTime = createTime;
    }

    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDisasterName() { return disasterName; }
    public void setDisasterName(String disasterName) { this.disasterName = disasterName; }
    public Integer getDisasterLevel() { return disasterLevel; }
    public void setDisasterLevel(Integer disasterLevel) { this.disasterLevel = disasterLevel; }
    public String getDisasterArea() { return disasterArea; }
    public void setDisasterArea(String disasterArea) { this.disasterArea = disasterArea; }
    public Integer getAffectPeople() { return affectPeople; }
    public void setAffectPeople(Integer affectPeople) { this.affectPeople = affectPeople; }
    public Long getReportUserId() { return reportUserId; }
    public void setReportUserId(Long reportUserId) { this.reportUserId = reportUserId; }
    public String getApproveDept() { return approveDept; }
    public void setApproveDept(String approveDept) { this.approveDept = approveDept; }
    public Long getApproveUserId() { return approveUserId; }
    public void setApproveUserId(Long approveUserId) { this.approveUserId = approveUserId; }
    public Integer getApproveStatus() { return approveStatus; }
    public void setApproveStatus(Integer approveStatus) { this.approveStatus = approveStatus; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public String getApproveRemark() {
        return approveRemark;
    }
    public void setApproveRemark(String approveRemark) {
        this.approveRemark = approveRemark;
    }
    @Override
    public String toString() {
        return "Disaster{" +
                "id=" + id +
                ", disasterName='" + disasterName + '\'' +
                ", disasterLevel=" + disasterLevel +
                ", disasterArea='" + disasterArea + '\'' +
                ", affectPeople=" + affectPeople +
                ", reportUserId=" + reportUserId +
                ", approveDept='" + approveDept + '\'' +
                ", approveUserId=" + approveUserId +
                ", approveStatus=" + approveStatus +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}