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
 * @Date: 2020/9/7 10:16
 * @Description: 角色表
 */
@Data
@TableName(value = "sys_role")
@ApiModel(value = "角色实体",description = "sys_role")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述")
    private String description;

}
