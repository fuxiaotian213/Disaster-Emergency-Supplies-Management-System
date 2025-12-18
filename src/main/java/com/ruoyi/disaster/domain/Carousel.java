package com.ruoyi.disaster.domain;

import java.util.Date;

/**
 * 轮播图表实体类（对应数据库 sys_carousel 表）
 */
public class Carousel {
    /** 轮播图唯一标识 */
    private Long id;
    /** 轮播标题 */
    private String title;
    /** 轮播内容 */
    private String content;
    /** 图片地址 */
    private String imageUrl;
    /** 显示顺序（数字越小越靠前） */
    private Integer sort;
    /** 状态（0-禁用/1-启用） */
    private Integer status;
    /** 发布部门 */
    private String publishDept;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    private transient Integer enabled;
    // 无参构造
    public Carousel() {}
    public Integer getEnabled() {
        return enabled;
    }
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
        // 自动映射 enabled 到 status
        if (enabled != null) {
            this.status = enabled;
        }
    }
    // 全参构造
    public Carousel(Long id, String title, String content, String imageUrl, Integer sort, Integer status,
                    String publishDept, Date createTime, Date updateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.sort = sort;
        this.status = status;
        this.publishDept = publishDept;
        this.createTime = createTime;
        this.updateTime = updateTime;

    }

    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Integer getSort() { return sort; }
    public void setSort(Integer sort) { this.sort = sort; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public String getPublishDept() { return publishDept; }
    public void setPublishDept(String publishDept) { this.publishDept = publishDept; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

    @Override
    public String toString() {
        return "Carousel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                ", publishDept='" + publishDept + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }


    private String oldImageUrl;

    /**
     * 获取旧图片地址
     * @return 旧图片地址
     */
    public String getOldImageUrl() {
        return oldImageUrl;
    }

    /**
     * 设置旧图片地址
     * @param oldImageUrl 旧图片地址（前端传递）
     */
    public void setOldImageUrl(String oldImageUrl) {
        this.oldImageUrl = oldImageUrl;
    }
}