package com.infra.server.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @Author: zzd
 * @Date: 2020/12/7 15:24
 * @Description: gateway跨域配置
 */
@Configuration
@EnableConfigurationProperties(GlobalCorsProperties.class)
public class CorsConfig {

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @RefreshScope
    @Bean
    public CorsWebFilter corsWebFilter(GlobalCorsProperties globalCorsProperties){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        globalCorsProperties.getCorsConfigurations().forEach(source::registerCorsConfiguration);
        return new CorsWebFilter(source);
    }
}
