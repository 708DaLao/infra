package com.infra.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infra.server.entity.SysUserAuth;
import com.infra.server.mapper.SysUserAuthMapper;
import com.infra.server.service.SysUserAuthService;
import org.springframework.stereotype.Service;

/**
 * @Author: gisocn
 * @Date: 2020/10/27
 * @Description:
 **/
@Service
public class SysUserAuthServiceImpl extends ServiceImpl<SysUserAuthMapper, SysUserAuth> implements SysUserAuthService {
}
