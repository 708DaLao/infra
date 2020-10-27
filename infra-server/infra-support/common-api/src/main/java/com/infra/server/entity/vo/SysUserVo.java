package com.infra.server.entity.vo;

import com.infra.server.entity.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: gisocn
 * @Date: 2020/10/26
 * @Description: 用户表及其鉴权表相关苏醒
 **/
@Data
public class SysUserVo extends SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * susAuth表相关属性
     */
    private String username;
    private Boolean status;
    private Date loginTime;
    private String loginIp;
    private Boolean online;



}
