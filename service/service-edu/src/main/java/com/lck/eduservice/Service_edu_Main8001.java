package com.lck.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/***
 #Create by LCK on 2022/3/3
 # 用法:
 */
@SpringBootApplication
@EnableDiscoveryClient //服务发现功能
@ComponentScan(basePackages = {"com.lck"})
@EnableFeignClients // 调用端添加此注解   service-edu 调 service-vod
public class Service_edu_Main8001 {
    public static void main(String[] args) {
        SpringApplication.run(Service_edu_Main8001.class, args);
    }
}
