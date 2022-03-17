package com.lck.cmsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/***
 #Create by LCK on 2022/3/11
 # 用法:
 */
@SpringBootApplication
@ComponentScan("com.lck")
@MapperScan("com/lck/cmsservice/mapper")
public class Service_cms_Main8004 {
    public static void main(String[] args) {
        SpringApplication.run(Service_cms_Main8004.class, args);
    }
}
