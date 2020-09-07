package com.infra.server.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zzd
 * @Date: 2020/9/7 16:47
 * @Description: 菜单路由表
 */
@Data
@TableName(value = "sys_router")
public class SysRouter implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 路劲
     */
    private String path;
    /**
     * 路由名称
     */
    private String name;
    /**
     * 组件路劲
     */
    private String component;
    /**
     * 元数据（包含title,icon等）
     */
    private String meta;
    /**
     * 重定向路劲
     */
    private String redirect;
    /**
     * 父id
     */
    private Integer pid;
    /**
     * 是否隐藏（是true，否false）
     */
    private Boolean hidden;
    /**
     * 是否显示跟菜单
     */
    private Boolean alwaysShow;
    /**
     * 菜单路由排序
     */
    private Integer sort;

}
