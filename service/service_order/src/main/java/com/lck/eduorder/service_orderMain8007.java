package com.lck.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/***
 #Create by LCK on 2022/3/15
 # 用法:
 */
@SpringBootApplication
@ComponentScan("com.lck")
@EnableDiscoveryClient //服务发现功能
@EnableFeignClients
@MapperScan("com.lck.eduorder.mapper")
public class service_orderMain8007 {
    public static void main(String[] args){
              SpringApplication.run(service_orderMain8007.class,args);
            }
}
