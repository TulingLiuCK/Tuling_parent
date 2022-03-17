package com.lck.staservice.client;

import com.lck.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/***
 #Create by LCK on 2022/3/17
 # 用法:
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @GetMapping("/serviceUcenter/ucenter-member/countRegistor/{day}")
    public R countRegister(@PathVariable("day") String day);
}
