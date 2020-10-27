package com.infra.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infra.server.entity.SysUser;
import com.infra.server.entity.SysUserAuth;
import com.infra.server.entity.vo.SysUserVo;
import com.infra.server.mapper.SysUserAuthMapper;
import com.infra.server.mapper.SysUserMapper;
import com.infra.server.service.SysUserAuthService;
import com.infra.server.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: gisocn
 * @Date: 2020/9/30
 * @Description:
 **/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserAuthMapper sysUserAuthMapper;

    @Override
    public IPage<SysUserVo> getUserList(IPage<SysUserVo> page) {
        return this.baseMapper.getUserList(page);
    }

    // 事务处理插入用户数据
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(SysUser sysUser, SysUserAuth sysUserAuth) {
        sysUserService.save(sysUser);
        sysUserAuth.setUserId(sysUser.getId());
        return sysUserAuthMapper.insert(sysUserAuth);
    }
}
