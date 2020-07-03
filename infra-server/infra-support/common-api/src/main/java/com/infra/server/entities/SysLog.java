package com.infra.server.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zzd
 * @Date: 2020/7/3 17:01
 * @Description: 日志实体类
 */
@Data
@TableName(value = "sys_log")
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 请求者id
     **/
    private Long userId;
    /**
     * 日志类型
     **/
    private String type;
    /**
     * 日志描述
     **/
    private String description;
    /**
     * 请求ip
     **/
    private String ip;
    /**
     * 请求url
     **/
    private String requestUrl;
    /**
     * 请求方法
     **/
    private String method;
    /**
     * 请求参数
     **/
    private String params;
    /**
     * 请求异常
     **/
    private String exception;
    /**
     * 请求时间
     **/
    private Data createTime;

}
