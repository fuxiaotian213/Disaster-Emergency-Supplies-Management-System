package com.ruoyi.disaster.service.Impl;

import com.ruoyi.disaster.domain.User;
import com.ruoyi.disaster.mapper.UserMapper;
import com.ruoyi.disaster.service.UserService;
import com.ruoyi.disaster.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.disaster.util.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 用户Service实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 密码加密器
    private final PasswordEncoder passwordEncoder = new PasswordEncoder();

    @Override
    public User getUserById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("用户ID不能为空");
        }
        User user = userMapper.queryById(id);
        if (ObjectUtils.isEmpty(user)) {
            throw new ServiceException("未查询到ID为" + id + "的用户");
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        if (ObjectUtils.isEmpty(username)) {
            throw new ServiceException("用户名不能为空");
        }
        return userMapper.queryByUsername(username);
    }

    @Override
    public List<User> getUserByRoleId(Long roleId) {
        if (ObjectUtils.isEmpty(roleId)) {
            throw new ServiceException("角色ID不能为空");
        }
        return userMapper.queryByRoleId(roleId);
    }

    @Override
    public void addUser(User user) {
        // 参数校验
        if (ObjectUtils.isEmpty(user.getUsername())) {
            throw new ServiceException("用户名不能为空");
        }
        if (ObjectUtils.isEmpty(user.getPassword())) {
            throw new ServiceException("密码不能为空");
        }
        if (ObjectUtils.isEmpty(user.getPhone())) {
            throw new ServiceException("联系电话不能为空");
        }
        if (ObjectUtils.isEmpty(user.getRoleId())) {
            throw new ServiceException("角色ID不能为空");
        }
        // 校验用户名是否已存在
        User existUser = getUserByUsername(user.getUsername());
        if (!ObjectUtils.isEmpty(existUser)) {
            throw new ServiceException("用户名" + user.getUsername() + "已存在");
        }
        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.saveUser(user);
    }

    @Override
    public void modifyUser(User user) {
        if (ObjectUtils.isEmpty(user.getId())) {
            throw new ServiceException("用户ID不能为空");
        }
        // 校验用户是否存在
        getUserById(user.getId());
        userMapper.updateUser(user);
    }

    @Override
    public void resetPassword(Long id, String newPassword) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("用户ID不能为空");
        }
        if (ObjectUtils.isEmpty(newPassword)) {
            throw new ServiceException("新密码不能为空");
        }
        // 校验用户是否存在
        getUserById(id);
        // 密码加密
        String encodedPassword = passwordEncoder.encode(newPassword);
        userMapper.updatePassword(id, encodedPassword);
    }


    @Override
    public List<User> searchUser(String username, Long roleId) {
        return userMapper.selectUserByCondition(username, roleId);
    }



    @Override
    public List<User> getAllUser() {
        // 调用Mapper层查询所有用户
        return userMapper.selectAllUser();
    }
    @Override
    public void deleteUserById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ServiceException("用户ID不能为空");
        }
        getUserById(id);
        userMapper.deleteUser(id);
    }

    @Override
    public boolean loginVerify(String username, String password) {
        User user = getUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            return false;
        }
        // 校验密码
        return passwordEncoder.matches(password, user.getPassword());
    }
}