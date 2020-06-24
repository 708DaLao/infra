package com.infra.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * author: strongmap
 * date: 2020/6/24
 * description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayRun {

    public static void main(String[] args) {
        SpringApplication.run(GatewayRun.class,args);
    }
}
