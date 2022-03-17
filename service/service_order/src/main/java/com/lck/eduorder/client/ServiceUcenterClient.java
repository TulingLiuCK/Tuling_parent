package com.lck.eduorder.client;

import com.lck.commonutils.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/***
 #Create by LCK on 2022/3/15
 # 用法:
 */
@Component
@FeignClient(name = "service-ucenter")
public interface ServiceUcenterClient {
    //根据用户id，获取用户信息
    @PostMapping("/serviceUcenter/ucenter-member/getMemberInfoById/{id}")
    public UcenterMemberVo getMemberInfoById(@PathVariable("id")String id);
}
