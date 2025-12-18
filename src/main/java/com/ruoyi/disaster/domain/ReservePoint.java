package com.ruoyi.disaster.domain;

import java.util.Date;

/**
 * 物资储备点表实体类（对应数据库 biz_reserve_point 表）
 */
public class ReservePoint {
    /** 储备点唯一标识 */
    private Long id;
    /** 储备点名称 */
    private String pointName;
    /** 省份 */
    private String province;
    /** 城市 */
    private String city;
    /** 详细地址 */
    private String address;
    /** 联系人 */
    private String contactPerson;
    /** 联系电话 */
    private String contactPhone;
    /** 管理部门 */
    private String manageDept;
    /** 创建时间 */
    private Date createTime;

    // 无参构造
    public ReservePoint() {}

    // 全参构造
    public ReservePoint(Long id, String pointName, String province, String city, String address,
                        String contactPerson, String contactPhone, String manageDept, Date createTime) {
        this.id = id;
        this.pointName = pointName;
        this.province = province;
        this.city = city;
        this.address = address;
        this.contactPerson = contactPerson;
        this.contactPhone = contactPhone;
        this.manageDept = manageDept;
        this.createTime = createTime;
    }

    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPointName() { return pointName; }
    public void setPointName(String pointName) { this.pointName = pointName; }
    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public String getManageDept() { return manageDept; }
    public void setManageDept(String manageDept) { this.manageDept = manageDept; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    @Override
    public String toString() {
        return "ReservePoint{" +
                "id=" + id +
                ", pointName='" + pointName + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", manageDept='" + manageDept + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}