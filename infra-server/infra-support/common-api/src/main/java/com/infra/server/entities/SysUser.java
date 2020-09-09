package com.infra.server.entities;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zzd
 * @Date: 2020/9/5 9:16
 * @Description: 用户基础信息实体
 */
@Data
@TableName(value = "sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *  指定主键生成策略使用雪花算法
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     *  昵称
    **/
    private String nickname;
    /**
     * 真实姓名
    **/
    private String realname;
    /**
     * 角色
    **/
    private String role;
    /**
     * 头像
    **/
    private String avatar;
    /**
     * 手机
    **/
    private Integer phone;
    /**
     * 邮箱
    **/
    private String email;
    /**
     * 性别（男1，女2，保密0）
    **/
    private Integer gender;
    /**
     * 出生日期
    **/
    private String birthday;
    /**
     * 住址
    **/
    private String addr;
    /**
     * 是否在线（是1，否0）
    **/
    private Boolean online;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;



}