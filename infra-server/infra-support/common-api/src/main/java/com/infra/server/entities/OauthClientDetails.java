package com.infra.server.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zzd
 * @Date: 2020/9/8 10:50
 * @Description: 客户端信息表
 */
@Data
@TableName(value = "oauth_client_details")
@ApiModel(value = "客户端详情",description = "oauth_client_details")
public class OauthClientDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 客户端id
     * 在实际应用中的另一个名称叫appKey
     */
    @TableId
    @TableField(value = "client_id")
    @ApiModelProperty(value = "客户端id，在实际应用中的另一个名称叫appKey")
    private String clientId;
    /**
     * 资源id集合，多个资源时用逗号分隔
     */
    @ApiModelProperty(value = "资源id集合，多个资源时用逗号分隔")
    private String resourceIds;
    /**
     * 客户端秘钥
     * 在实际应用中的另一个名称叫appSecret
     */
    @ApiModelProperty(value = "客户端秘钥，在实际应用中的另一个名称叫appSecret")
    private String clientSecret;
    /**
     * 客户端申请权限范围
     * 常用的值为read,write.
     */
    @ApiModelProperty(value = "客户端申请权限范围",example = "read,write")
    private String scope;
    /**
     * 客户端支持的grant_type
     * 授权码模式:authorization_code,密码模式:password,刷新token: refresh_token, 隐式模式: implicit: 客户端模式: client_credentials。支持多个用逗号分隔
     */
    @ApiModelProperty(value = "客户端支持的grant_type",example = "password,authorization_code,refresh_token,implicit,client_credentials")
    private String authorizedGrantTypes;
    /**
     * 客户端重定向uri，authorization_code和implicit需要该值进行校验，注册时填写
     */
    @ApiModelProperty(value = "客户端重定向uri")
    private String webServerRedirectUri;
    /**
     * 客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔
     */
    @ApiModelProperty(value = "客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔")
    private String authorities;
    /**
     * 访问令牌有效时间值(单位:秒),默认12小时
     */
    @ApiModelProperty(value = "访问令牌有效时间值(单位:秒),默认12小时")
    private Integer accessTokenValidity;
    /**
     * 更新令牌有效时间值(单位:秒)，默认30天
     */
    @ApiModelProperty(value = "更新令牌有效时间值(单位:秒)，默认30天")
    private Integer refreshTokenValidity;
    /**
     * 预留字段，值必须是json格式
     */
    @ApiModelProperty(value = "预留字段，值必须是json格式")
    private String additionalInformation;
    /**
     * 用户是否自动Approval操作，默认false
     * 适用于authorization_code模式,设置用户是否自动approval操作,设置true跳过用户确认授权操作页面，直接跳到redirect_uri
     */
    @ApiModelProperty(value = "用户是否自动Approval操作，默认false,适用于authorization_code模式,设置用户是否自动approval操作,设置true跳过用户确认授权操作页面，直接跳到redirect_uri")
    private String autoapprove;

}
