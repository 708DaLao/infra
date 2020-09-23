package com.infra.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: gisocn
 * @Date: 2020/9/23
 * @Description: 角色路由关联表
 **/
@Data
@TableName(value = "sys_role_router")
@ApiModel(value = "角色路由关联表",description = "sys_role_router")
public class SysRoleRouter implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "路由id")
    private Integer routerId;

}
