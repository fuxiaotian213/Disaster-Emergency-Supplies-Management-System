package com.ruoyi.disaster.config;

import com.ruoyi.disaster.util.AjaxResult;
import com.ruoyi.disaster.util.HttpStatus;
import com.ruoyi.disaster.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 * 统一处理系统中的所有异常，返回标准的AjaxResult格式
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理业务异常
     * 业务异常通常是客户端错误，返回400状态码
     */
    @ExceptionHandler(ServiceException.class)
    public AjaxResult handleServiceException(ServiceException e, HttpServletRequest request) {
        log.error("业务异常：{}，请求路径：{}", e.getMessage(), request.getRequestURI());
        return AjaxResult.error(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    /**
     * 处理HTTP请求方法不支持异常
     * 这是导致405错误的主要原因
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public AjaxResult handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error("HTTP请求方法不支持：{}，请求路径：{}，支持的方法：{}", 
                e.getMessage(), request.getRequestURI(), e.getSupportedHttpMethods());
        return AjaxResult.error(HttpStatus.BAD_METHOD, "请求方法错误，请检查请求类型");
    }

    /**
     * 处理其他所有异常
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e, HttpServletRequest request) {
        log.error("系统异常：{}，请求路径：{}", e.getMessage(), request.getRequestURI(), e);
        return AjaxResult.error("服务器内部错误，请稍后重试");
    }
}