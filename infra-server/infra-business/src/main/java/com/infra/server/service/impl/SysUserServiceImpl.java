package com.infra.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infra.server.entity.SysUser;
import com.infra.server.mapper.SysUserMapper;
import com.infra.server.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @Author: gisocn
 * @Date: 2020/9/30
 * @Description:
 **/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
