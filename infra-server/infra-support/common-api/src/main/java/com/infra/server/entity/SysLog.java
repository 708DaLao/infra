package com.infra.server.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zzd
 * @Date: 2020/7/3 17:01
 * @Description: 日志实体类
 */
@Data
@TableName(value = "sys_log")
@ApiModel(value = "日志实体类",description = "sys_log")
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 请求者id
     **/
    @ApiModelProperty(value = "请求者id即用户id")
    private Long userId;
    /**
     * 日志类型
     **/
    @ApiModelProperty(value = "日志类型")
    private String type;
    /**
     * 日志描述
     **/
    @ApiModelProperty(value = "日志描述")
    private String description;
    /**
     * 请求ip
     **/
    @ApiModelProperty(value = "请求者ip")
    private String ip;
    /**
     * 请求url
     **/
    @ApiModelProperty(value = "请求url")
    private String requestUrl;
    /**
     * 请求方法
     **/
    @ApiModelProperty(value = "请求方法")
    private String method;
    /**
     * 请求参数
     **/
    @ApiModelProperty(value = "请求参数")
    private String params;
    /**
     * 请求异常
     **/
    @ApiModelProperty(value = "请求异常")
    private String exception;
    /**
     * 请求时间
     **/
    @ApiModelProperty(value = "请求时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

}
