package com.infra.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: zzd
 * @Date: 2020/7/3 14:55
 * @Description: 自定义日志注解类
 */
@Retention(RetentionPolicy.RUNTIME) //注解生命周期
@Target({ElementType.PARAMETER,ElementType.METHOD}) //注解使用范围
public @interface InfraLog {

    /**
     * @return 功能名称
     * @date 2020/7/3 15:29
    **/
    String description() default "";

    /**
     * @return 是否保存（默认保存）
     * @date 2020/7/3 15:33
    **/
    boolean isSave() default true;


}
