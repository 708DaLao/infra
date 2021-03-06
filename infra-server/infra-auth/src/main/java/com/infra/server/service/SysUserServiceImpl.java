package com.infra.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.infra.server.exception.CustomException;
import com.infra.server.api.ResultCodeEnum;
import com.infra.server.entity.SysRole;
import com.infra.server.entity.SysUserAuth;
import com.infra.server.mapper.SysUserAuthMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zzd
 * @Date: 2020/9/7 9:02
 * @Description:
 * ClientDetailsServiceImpl和SysUserServiceImpl
 * 用户登录时（即携带参数请求/oauth/token接口）会调用这两个service。
 * ClientDetailsServiceImpl是根据client_id查出来的信息验证用户登录时携带的参数（即客户端详情表信息）是否正确。并且设置能访问的资源id集合。
 * SysUserServiceImpl根据用户名去查用户密码，用户所拥有的角色等信息，然后丢给security去验证用户登录时的用户名和密码是否正确
 */
@Service
public class SysUserServiceImpl implements UserDetailsService {

    @Resource
    private SysUserAuthMapper sysUserAuthMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUserAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        // 根据用户名即账号查询
        SysUserAuth sysUserAuth = sysUserAuthMapper.selectOne(queryWrapper);
        if (sysUserAuth == null ) {
            throw new CustomException(ResultCodeEnum.NULL_USERNAME);
        }

        // 账户已被锁定则抛出异常
        if (!sysUserAuth.getStatus()) {
            throw new CustomException(ResultCodeEnum.ACCOUNT_LOCKED);
        }
        // 查询当前用户拥有的角色
        List<SysRole> roles = sysUserAuthMapper.getRoleByUserId(sysUserAuth.getUserId());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (roles.size() != 0 ) {
            for (SysRole role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }

        User user = new User(username, sysUserAuth.getPassword(), authorities);
        return user;
    }
}
