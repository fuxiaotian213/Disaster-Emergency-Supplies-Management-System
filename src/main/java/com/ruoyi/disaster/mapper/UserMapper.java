package com.ruoyi.disaster.mapper;

import com.ruoyi.disaster.domain.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户Mapper接口
 */
public interface UserMapper {
    // 1. 按ID查询用户
    User queryById(Long id);

    // 2. 按用户名查询用户（登录用）
    User queryByUsername(String username);

    // 3. 按角色ID查询用户列表
    List<User> queryByRoleId(Long roleId);
    List<User> selectUserByCondition(@Param("username") String username, @Param("roleId") Long roleId);
    // 4. 新增用户
    void saveUser(User user);

    // 5. 修改用户信息
    void updateUser(User user);

    // 6. 修改密码
    void updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

    // 7. 删除用户
    void deleteUser(Long id);
    List<User> selectAllUser(
            @Param("username") String username,  // @Param注解用于SQL中参数绑定
            @Param("roleId") Long roleId
    );

    List<User> selectAllUser();
}