package com.infra.server.config;

import com.infra.server.component.JwtTokenEnhancer;
import com.infra.server.service.ClientDetailsServiceImpl;
import com.infra.server.service.SysUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zzd
 * @Date: 2020/9/5 17:11
 * @Description: 认证服务器配置
 * @EnableAuthorizationServer 告诉 Spring 这个应用是 OAuth2 的认证中心
 * 客户端信息存于数据库中，token信息存于redis
 * 指定认证中心，指定客户端， 指定token的存储方式等。
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private ClientDetailsServiceImpl clientDetails;
    @Resource
    private SysUserServiceImpl userService;
    @Resource
    private JwtTokenEnhancer jwtTokenEnhancer;

    /**
     * 定义客户端详情服务
     * 校验注册的第三方客户端的信息，存储于数据库中，默认是在内存中
     * 存储在oauth_client_details表
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        // 从数据库中读取客户端数据
        clients.withClientDetails(clientDetails);
    }

    /**
     * 认证服务器令牌访问端点配置和令牌服务配置，可以替换默认的端点url，配置支持的授权模式
     * 默认的端点URL有以下6个：
     * /oauth/authorize : 授权端点。
     * /oauth/token : 令牌端点。
     * /oauth/confirm _access : 用户确认授权提交端点。
     * /oauth/error : 授权服务错误信息端点。
     * /oauth/check_token : 用于资源服务访问的令牌解析端点。
     * /oauth/token_key : 提供公有密匙的端点,如果你使用JWT令牌的话。
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //指定认证管理器
        endpoints.authenticationManager(authenticationManager);
        //将Token存放到Redis中
        endpoints.tokenStore(tokenStore());

        //配置加载用户信息的服务
        endpoints.userDetailsService(userService);

        // 配置JwtAccessToken转换器
        endpoints.accessTokenConverter(jwtAccessTokenConverter());

        // 自定义token生成方法
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(jwtAccessTokenConverter());
        enhancerChain.setTokenEnhancers(delegates);
        //配置JWT的内容增强器endpoints.tokenEnhancer(tokenEnhancerChain);
        endpoints.tokenEnhancer(enhancerChain);

    }

    /**
     * 配置安全约束，主要是对默认的6个端点的开启与关闭配置
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients(); // 允许表单认证
    }


    /**
     * 对Jwt签名时，增加一个密钥
     * JwtAccessTokenConverter：对Jwt来进行编码以及解码的类
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //对称加密方式
//        jwtAccessTokenConverter.setSigningKey("jwt_zzd");
        // 非对称加密
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    /**
     *  非对称加密方式
     */
    @Bean
    public KeyPair keyPair() {
        //从classpath下的证书中获取秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "infra123".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "infra123".toCharArray());

    }

    /**
     * 使用redis来存储token
     * Token的可选存储方式
     * 1、InMemoryTokenStore  内存
     * 2、JdbcTokenStore  数据库
     * 3、JwtTokenStore  jwt
     * 4、RedisTokenStore  redis
     */
    @Bean
    public TokenStore tokenStore() {
//        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
//        tokenStore.setPrefix("user-token:");
//        return tokenStore;
        return new RedisTokenStore(redisConnectionFactory);
    }

}
