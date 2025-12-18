package com.ruoyi.disaster.domain;

import java.util.Date;

/**
 * 用户表实体类（对应数据库 user 表）
 */
public class User {
    /** 用户唯一标识 */
    private Long id;
    /** 登录账号 */
    private String username;
    /** 加密存储密码 */
    private String password;
    /** 真实姓名 */
    private String realName;
    /** 联系电话 */
    private String phone;
    /** 角色ID（1-救援人员/2-物资管理员/3-受灾群众/4-政府部门） */
    private Long roleId;
    /** 所属单位 */
    private String unit;
    /** 地址 */
    private String address;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

    // 无参构造
    public User() {}

    // 全参构造
    public User(Long id, String username, String password, String realName, String phone, Long roleId,
                String unit, String address, Date createTime, Date updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.phone = phone;
        this.roleId = roleId;
        this.unit = unit;
        this.address = address;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRealName() { return realName; }
    public void setRealName(String realName) { this.realName = realName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", phone='" + phone + '\'' +
                ", roleId=" + roleId +
                ", unit='" + unit + '\'' +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}