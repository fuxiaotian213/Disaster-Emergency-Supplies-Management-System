package com.ruoyi.disaster.service;

import com.ruoyi.disaster.domain.User;
import java.util.List;

/**
 * 用户Service接口
 */
public interface UserService {
    // 按ID查询用户
    User getUserById(Long id);

    // 按用户名查询用户（登录用）
    User getUserByUsername(String username);

    // 按角色ID查询用户列表
    List<User> getUserByRoleId(Long roleId);
    List<User> getAllUser();
    // 新增用户
    void addUser(User user);

    // 修改用户信息
    void modifyUser(User user);

    // 修改密码
    void resetPassword(Long id, String newPassword);
    List<User> searchUser(String username, Long roleId);
    // 删除用户
    void deleteUserById(Long id);

    // 登录验证（密码校验）
    boolean loginVerify(String username, String password);
}