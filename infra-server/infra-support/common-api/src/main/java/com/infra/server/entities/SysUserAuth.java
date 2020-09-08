package com.infra.server.entities;

import com.baomidou.mybatisplus.annotation.*;
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
public class SysUserAuth implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     *  用户id
    **/
    private Long userId;
    /**
     * 账号
    **/
    private String username;
    /**
     * 密码
    **/
    private String password;
    /**
     * 状态（开启1，锁定0）
    **/
    private Boolean status;
    /**
     * 上次登录时间
    **/
    private Date loginTime;
    /**
     * 上次登录ip
    **/
    private String loginIp;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
