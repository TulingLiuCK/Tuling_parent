package com.lck.serviceUcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/***
 #Create by LCK on 2022/3/12
 # 用法:
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.lck")
@EnableDiscoveryClient
@MapperScan("com.lck.serviceUcenter.mapper")
public class service_ucenterMain8006 {
    public static void main(String[] args){
              SpringApplication.run(service_ucenterMain8006.class,args);
            }
}
