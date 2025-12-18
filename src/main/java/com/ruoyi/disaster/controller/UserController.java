package com.ruoyi.disaster.controller;

import com.ruoyi.disaster.domain.User;
import com.ruoyi.disaster.service.UserService;
import com.ruoyi.disaster.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.disaster.util.Anonymous;
import java.util.List;

/**
 * 用户Controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 按ID查询用户 - 支持GET和POST
    @Anonymous
    @RequestMapping(value = "/getUserById", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getUserById(Long id) {
        User user = userService.getUserById(id);
        return AjaxResult.success(user);
    }

    // 按用户名查询用户（登录用） - 支持GET和POST
    @Anonymous
    @RequestMapping(value = "/getUserByUsername", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getUserByUsername(String username) {
        User user = userService.getUserByUsername(username);
        return AjaxResult.success(user);
    }

    // 按角色ID查询用户列表 - 支持GET和POST
    @Anonymous
    @RequestMapping(value = "/getUserByRoleId", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getUserByRoleId(Long roleId) {
        List<User> userList = userService.getUserByRoleId(roleId);
        return AjaxResult.success(userList);
    }

    // 新增：查询所有用户（支持按用户名、角色ID模糊搜索） - 支持GET和POST
    @Anonymous
    @RequestMapping(value = "/getAllUser", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getAllUser() {
        List<User> allUserList = userService.getAllUser();
        return AjaxResult.success(allUserList);
    }
    //带条件查询用户（支持用户名模糊搜索、角色ID精确匹配） - 支持GET和POST
    @Anonymous
    @RequestMapping(value = "/searchUser", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult searchUser(@RequestParam(required = false) String username,
                                 @RequestParam(required = false) Long roleId) {
        List<User> userList = userService.searchUser(username, roleId);
        return AjaxResult.success(userList);
    }

    // 新增用户 - 支持GET和POST
    @Anonymous
    @RequestMapping(value = "/addUser", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult addUser(@RequestBody User user) {
        userService.addUser(user);
        return AjaxResult.success("用户新增成功");
    }

    // 修改用户信息 - 支持GET和POST
    @Anonymous
    @RequestMapping(value = "/modifyUser", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult modifyUser(@RequestBody User user) {
        userService.modifyUser(user);
        return AjaxResult.success("用户修改成功");
    }

    // 重置密码 - 支持GET和POST
    @Anonymous
    @RequestMapping(value = "/resetPassword", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult resetPassword(Long id, String newPassword) {
        userService.resetPassword(id, newPassword);
        return AjaxResult.success("密码重置成功");
    }

    // 登录验证 - 同时支持GET和POST方法
    @Anonymous
    @RequestMapping(value = "/loginVerify", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult loginVerify(String username, String password) {
        boolean verifyResult = userService.loginVerify(username, password);
        if (verifyResult) {
            User user = userService.getUserByUsername(username);
            return AjaxResult.success("登录成功", user);
        }
        // 业务错误：用户名或密码错误，返回401未授权，而不是500服务器内部错误
        return AjaxResult.error(401, "用户名或密码错误");
    }

    // 删除用户 - 支持GET、POST和DELETE
    @Anonymous
    @RequestMapping(value = "/deleteUser", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public AjaxResult deleteUser(Long id) {
        userService.deleteUserById(id);
        return AjaxResult.success("用户删除成功");
    }
}