package com.ruoyi.disaster.controller;

import com.ruoyi.disaster.util.AjaxResult;
import com.ruoyi.disaster.util.Anonymous;
import org.springframework.web.bind.annotation.*;

/**
 * 测试控制器，用于验证不同HTTP方法的响应
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 测试GET方法
     */
    @Anonymous
    @GetMapping("/get")
    public AjaxResult testGet() {
        return AjaxResult.success("GET方法测试成功");
    }

    /**
     * 测试POST方法
     */
    @Anonymous
    @PostMapping("/post")
    public AjaxResult testPost() {
        return AjaxResult.success("POST方法测试成功");
    }

    /**
     * 测试PUT方法
     */
    @Anonymous
    @PutMapping("/put")
    public AjaxResult testPut() {
        return AjaxResult.success("PUT方法测试成功");
    }

    /**
     * 测试DELETE方法
     */
    @Anonymous
    @DeleteMapping("/delete")
    public AjaxResult testDelete() {
        return AjaxResult.success("DELETE方法测试成功");
    }

    /**
     * 测试OPTIONS方法
     */
    @Anonymous
    @RequestMapping(value = "/options", method = RequestMethod.OPTIONS)
    public AjaxResult testOptions() {
        return AjaxResult.success("OPTIONS方法测试成功");
    }

    /**
     * 测试所有方法
     */
    @Anonymous
    @RequestMapping(value = "/all", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
    public AjaxResult testAllMethods() {
        return AjaxResult.success("所有方法测试成功");
    }
}