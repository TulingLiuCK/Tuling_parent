package com.lck.eduservice.client.impl;

import com.lck.commonutils.UcenterMemberVo;
import com.lck.eduservice.client.UcenterClient;
import org.springframework.stereotype.Component;

/***
 #Create by LCK on 2022/3/15
 # 用法: 兜底方法
 */
@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public UcenterMemberVo getMemberInfoById(String memberId) {
        return null;
    }
}
