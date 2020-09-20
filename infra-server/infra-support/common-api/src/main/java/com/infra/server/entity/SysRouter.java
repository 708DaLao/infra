package com.infra.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zzd
 * @Date: 2020/9/7 16:47
 * @Description: 菜单路由表
 */
@Data
@TableName(value = "sys_router")
@ApiModel(value = "菜单路由实体",description = "sys_router")
public class SysRouter implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 路由标题
     */
    @ApiModelProperty(value = "路由标题")
    private String title;
    /**
     * 路由图标
     */
    @ApiModelProperty(value = "路由图标")
    private String icon;
    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    private String path;
    /**
     * 路由名称，要缓存视图组件，必须设置唯一name
     */
    @ApiModelProperty(value = "路由名称，要缓存视图组件，必须设置唯一name")
    private String name;
    /**
     * 是否缓存视图组件
     */
    @ApiModelProperty(value = "是否缓存视图组件")
    private Boolean keepAlive;
    /**
     * 组件路劲
     */
    @ApiModelProperty(value = "组件路径")
    private String component;

    /**
     * 重定向路劲
     */
    @ApiModelProperty(value = "重定向路径")
    private String redirect;
    /**
     * 父id
     */
    @ApiModelProperty(value = "上级id")
    private Integer pid;
    /**
     * 是否隐藏（是true，否false）
     */
    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden;
    /**
     * 是否显示跟菜单
     */
    @ApiModelProperty(value = "是否显示根菜单（只有一个子路由时）")
    private Boolean alwaysShow;
    /**
     * 菜单路由排序
     */
    @ApiModelProperty(value = "菜单路由排序")
    private Integer sort;

}
