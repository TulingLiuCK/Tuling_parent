package com.lck.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/***
 #Create by LCK on 2022/3/17
 # 用法:
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan("com.lck")
@MapperScan("com.lck.staservice.mapper")
//@EnableScheduling // 定时任务
public class Service_staservice_Main8008 {
    public static void main(String[] args){
              SpringApplication.run(Service_staservice_Main8008.class,args);
            }
}
