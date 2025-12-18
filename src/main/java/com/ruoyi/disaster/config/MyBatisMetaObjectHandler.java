package com.ruoyi.disaster.config;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 自定义MyBatis MetaObjectHandler，用于自动填充createTime和updateTime
 * 注意：这是原生MyBatis的实现方式，不是MyBatis Plus
 */
@Component
public class MyBatisMetaObjectHandler {
    
    /**
     * 在插入前自动填充createTime和updateTime
     */
    public static void handleInsert(MetaObject metaObject) {
        Date now = new Date();
        if (metaObject.hasSetter("createTime")) {
            metaObject.setValue("createTime", now);
        }
        if (metaObject.hasSetter("updateTime")) {
            metaObject.setValue("updateTime", now);
        }
    }
    
    /**
     * 在更新前自动填充updateTime
     */
    public static void handleUpdate(MetaObject metaObject) {
        if (metaObject.hasSetter("updateTime")) {
            metaObject.setValue("updateTime", new Date());
        }
    }
    
    /**
     * 自定义ObjectWrapperFactory，用于处理Map类型的参数
     */
    public static class CustomObjectWrapperFactory implements ObjectWrapperFactory {
        @Override
        public boolean hasWrapperFor(Object object) {
            return object instanceof Map;
        }
        
        @Override
        public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
            return new MapWrapper(metaObject, (Map<String, Object>) object);
        }
    }
}