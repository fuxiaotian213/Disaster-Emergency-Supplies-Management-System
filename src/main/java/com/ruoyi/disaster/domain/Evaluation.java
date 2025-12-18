package com.ruoyi.disaster.domain;

import java.util.Date;

/**
 * 评价反馈表实体类（对应数据库 biz_evaluation 表）
 */
public class Evaluation {
    /** 评价唯一标识 */
    private Long id;
    /** 关联订单ID（关联biz_order表） */
    private Long orderId;
    /** 评价人ID（关联user表） */
    private Long userId;
    /** 物资适配度（1-5分） */
    private Integer materialAdapt;
    /** 调度效率（1-5分） */
    private Integer dispatchEfficiency;
    /** 对政府调度工作的建议 */
    private String govSuggestion;
    /** 政府是否反馈（0-未反馈/1-已反馈） */
    private Integer isGovFeedback;
    /** 评价内容 */
    private String content;
    /** 评价时间 */
    private Date createTime;


    /** 反馈类型：1-功能建议/2-系统问题/3-服务投诉/4-其他反馈 */
    private Integer feedbackType;
    /** 反馈标题 */
    private String title;
    /** 处理状态：0-待处理/1-已处理 */
    private Integer status;
    /** 处理回复 */
    private String reply;
    /** 更新时间 */
    private Date updateTime;





    // 无参构造
    public Evaluation() {}

    // 全参构造
    public Evaluation(Long id, Long orderId, Long userId, Integer materialAdapt, Integer dispatchEfficiency,
                      String govSuggestion, Integer isGovFeedback, String content, Date createTime) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.materialAdapt = materialAdapt;
        this.dispatchEfficiency = dispatchEfficiency;
        this.govSuggestion = govSuggestion;
        this.isGovFeedback = isGovFeedback;
        this.content = content;
        this.createTime = createTime;
        this.feedbackType = feedbackType;
        this.title = title;
        this.status = status;
        this.reply = reply;
        this.updateTime = updateTime;
    }

    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Integer getMaterialAdapt() { return materialAdapt; }
    public void setMaterialAdapt(Integer materialAdapt) { this.materialAdapt = materialAdapt; }
    public Integer getDispatchEfficiency() { return dispatchEfficiency; }
    public void setDispatchEfficiency(Integer dispatchEfficiency) { this.dispatchEfficiency = dispatchEfficiency; }
    public String getGovSuggestion() { return govSuggestion; }
    public void setGovSuggestion(String govSuggestion) { this.govSuggestion = govSuggestion; }
    public Integer getIsGovFeedback() { return isGovFeedback; }
    public void setIsGovFeedback(Integer isGovFeedback) { this.isGovFeedback = isGovFeedback; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Integer getFeedbackType() { return feedbackType; }
    public void setFeedbackType(Integer feedbackType) { this.feedbackType = feedbackType; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public String getReply() { return reply; }
    public void setReply(String reply) { this.reply = reply; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", materialAdapt=" + materialAdapt +
                ", dispatchEfficiency=" + dispatchEfficiency +
                ", govSuggestion='" + govSuggestion + '\'' +
                ", isGovFeedback=" + isGovFeedback +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", feedbackType=" + feedbackType +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", reply='" + reply + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}