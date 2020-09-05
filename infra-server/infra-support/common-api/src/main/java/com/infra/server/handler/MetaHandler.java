package com.infra.server.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: zzd
 * @Date: 2020/9/5 9:52
 * @Description: 创建时间之类的数据处理
 */
@Component
public class MetaHandler implements MetaObjectHandler {
    /**
     * 使用mp实现添加操作,这个方法会执行,metaObject元数据(表中的名字,表中的字段)
    **/
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    /**
     * 使用mp实现修改操作,这个方法会执行
    **/
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
