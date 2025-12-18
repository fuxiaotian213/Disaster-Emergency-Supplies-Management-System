package com.ruoyi.disaster.util;

import java.lang.annotation.*;

/**
 * 匿名访问注解，用于标记允许匿名访问的接口
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Anonymous {
}


