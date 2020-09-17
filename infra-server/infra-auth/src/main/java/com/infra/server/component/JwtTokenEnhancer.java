package com.infra.server.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.infra.server.entity.SysUserAuth;
import com.infra.server.mapper.SysUserAuthMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zzd
 * @Date: 2020/9/8 12:35
 * @Description: 自定义token生成携带的信息
 * TokenEhancer（令牌增强器）
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Resource
    private SysUserAuthMapper sysUserAuthMapper;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        // 获取当前登录的用户信息
        User user = (User) authentication.getPrincipal();
        QueryWrapper<SysUserAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        // 根据用户名即账号查询用户信息
        SysUserAuth sysUserAuth = sysUserAuthMapper.selectOne(queryWrapper);

        final Map<String, Object> additionalInfo = new HashMap<>();

        // 给/oauth/token接口加属性author等
        additionalInfo.put("author", "zzd");

        // 把用户id放进token
        additionalInfo.put("user_id",sysUserAuth.getUserId());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
