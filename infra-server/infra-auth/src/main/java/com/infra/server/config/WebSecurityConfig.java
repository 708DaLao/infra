package com.infra.server.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: zzd
 * @Date: 2020/9/6 17:30
 * @Description: Security安全配置
 * @EnableGlobalMethodSecurity 作用是开启方法注解权限配置
 * 例如：  @PreAuthorize("hasRole(‘admin‘)") 在某个方法上加上这个注解，说明只有admin角色的用户才可以访问
 * 不加则所有人都可以访问
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 安全拦截机制（最重要）
     * 一些访问规则：
     * http.authorizeRequests().antMatchers("/api/**").denyAll();    //拒绝访问
     * http.authorizeRequests().antMatchers("/api/**").authenticated();    //需认证通过
     * http.authorizeRequests().antMatchers("/api/**").permitAll();    //无条件允许访问
     * http.authorizeRequests().antMatchers(HttpMethod.GET).access("permitAll"); 表示所有的get请求都不需要权限认证
     * http.authorizeRequests().antMatchers(HttpMethod.POST, "/v1/world").hasRole("USER") 匹配/hello，且http method是POST，需要权限认证
     * requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()整合actuator对端点的访问进行限制规则设置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers("/rsa/public_key").permitAll()
                .anyRequest().authenticated();
    }

    /**
     * 认证管理区配置，密码模式需要用到
     *
     * @return AuthenticationManager
     * @throws Exception Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
