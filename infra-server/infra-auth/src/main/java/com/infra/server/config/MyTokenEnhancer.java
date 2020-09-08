package com.infra.server.config;

import cn.hutool.core.date.DateUtil;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zzd
 * @Date: 2020/9/8 12:35
 * @Description: 自定义token生成携带的信息
 * TokenEhancer（令牌增强器）
 */
public class MyTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        // 给/oauth/token接口加属性author等
        additionalInfo.put("author", "zzd");
        additionalInfo.put("createTime", DateUtil.formatDate(new Date()));

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
