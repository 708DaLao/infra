package com.infra.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.infra.server.mapper")
public class AuthRun {

    public static void main(String[] args) {
        SpringApplication.run(AuthRun.class,args);
    }
}
