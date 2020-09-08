package com.infra.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 安全拦截机制（最重要）
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //防csrf攻击
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/oauth/**").permitAll();

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

}
