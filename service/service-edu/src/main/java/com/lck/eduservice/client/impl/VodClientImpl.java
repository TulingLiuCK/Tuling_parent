package com.lck.eduservice.client.impl;

import com.lck.commonutils.R;
import com.lck.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/***
 #Create by LCK on 2022/3/10
 # 用法: 对于方法出错 可以对其进行降级 hystrix熔断机制
 */
@Component
public class VodClientImpl implements VodClient {
    //出错之后会执行
    @Override
    public R removeAliyunVideoById(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R removeBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错了");
    }
}
