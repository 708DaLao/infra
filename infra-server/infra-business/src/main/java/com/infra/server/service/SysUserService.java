package com.infra.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.infra.server.entity.SysUser;
import com.infra.server.entity.SysUserAuth;
import com.infra.server.entity.vo.SysUserVo;


/**
 * @Author: gisocn
 * @Date: 2020/9/30
 * @Description:
 **/
public interface SysUserService extends IService<SysUser> {

    /**
     * 获取用户列表
     */
    IPage<SysUserVo> getUserList(IPage<SysUserVo> page);

    /**
     * 添加用户
     */
    int addUser(SysUser sysUser, SysUserAuth sysUserAuth);
}
