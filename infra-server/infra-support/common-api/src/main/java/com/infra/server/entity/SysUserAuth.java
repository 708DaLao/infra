package com.infra.server.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zzd
 * @Date: 2020/9/5 10:23
 * @Description: 用户鉴权实体
 */
@Data
@TableName(value = "sys_user_auth")
@ApiModel(value = "用户鉴权实体",description = "sys_user_auth")
public class SysUserAuth implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     *  用户id
    **/
    @ApiModelProperty(value = "用户id")
    private Long userId;
    /**
     * 账号
    **/
    @ApiModelProperty(value = "账号/用户名")
    private String username;
    /**
     * 密码
    **/
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 状态（开启1，锁定0）
    **/
    @ApiModelProperty(value = "状态")
    private Boolean status;
    /**
     * 上次登录时间
    **/
    @ApiModelProperty(value = "上次登录时间")
    private Date loginTime;
    /**
     * 上次登录ip
    **/
    @ApiModelProperty(value = "上次登录Ip")
    private String loginIp;
    /**
     * 是否在线（是1，否0）
     **/
    @ApiModelProperty(value = "是否在线")
    private Boolean online;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
