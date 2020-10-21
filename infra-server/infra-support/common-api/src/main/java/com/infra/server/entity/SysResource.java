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
 * @Date: 2020/10/21
 * @Description: 资源，即接口
 **/
@Data
@TableName(value = "sys_resource")
@ApiModel(value = "资源实体",description = "sys_resource")
public class SysResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "拥有者")
    private String owner;

    @ApiModelProperty(value = "资源描述")
    private String description;


}
