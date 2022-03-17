package com.lck.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/***
 #Create by LCK on 2022/3/10
 # 用法:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.lck"}) //用于扫描swagger
public class Service_vod_main8003 {
    public static void main(String[] args) {
        SpringApplication.run(Service_vod_main8003.class, args);
    }
}
