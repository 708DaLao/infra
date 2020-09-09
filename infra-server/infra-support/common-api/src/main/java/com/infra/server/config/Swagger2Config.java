package com.infra.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: zzd
 * @Date: 2020/9/9 9:28
 * @Description: swagger配置
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    private boolean swagger_is_enable = true;

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swagger_is_enable)
                .apiInfo(buildApiInf())
                .select()
                // swagger 扫描 controller 包路径
                .apis(RequestHandlerSelectors.basePackage("com.infra.server.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("infra接口文档")
                .version("v1.0")
                .build();
    }
}
