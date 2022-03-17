package com.lck.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/***
 #Create by LCK on 2022/3/12
 # 用法:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //取消数据源自动配置
@ComponentScan("com.lck")
public class Service_msmMain8005 {
    public static void main(String[] args) {
        SpringApplication.run(Service_msmMain8005.class, args);
    }
}
