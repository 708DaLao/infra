package com.infra.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * date: 2020/6/16
 * description: 主启动
 * @author infra
 */
@SpringBootApplication
//@EnableDiscoveryClient
@RestController
@MapperScan("com.infra.server.mapper")
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
