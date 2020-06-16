package com.infra.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: zzd
 * date: 2020/6/16
 * description: 主启动
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class AppRun {

    public static void main(String[] args) {
        SpringApplication.run(AppRun.class,args);
    }

    @GetMapping("/")
    public String index(){
        System.out.println("--------业务服务启动成功-------");
        return "infra基础架构";
    }
}
