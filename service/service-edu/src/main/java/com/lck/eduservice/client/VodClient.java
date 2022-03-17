package com.lck.eduservice.client;

import com.lck.commonutils.R;
import com.lck.eduservice.client.impl.VodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/***
 #Create by LCK on 2022/3/10
 # 用法:
 */
@FeignClient(name = "service-vod",fallback = VodClientImpl.class)  //名字一定要是在service_vod项目中application.properties里面的名字
@Component
public interface VodClient {

    //定义调用的方法路径   路径一定要是完全路径 注意一下类名上的路径
    //删除视频
    @DeleteMapping("/eduvod/video/removeAliyunVideoById/{id}")
    public R removeAliyunVideoById(@PathVariable("id") String id); //@PathVariable 注解一定要带上参数名称

    //根据多个视频id删除多个阿里云视频
    @DeleteMapping("/eduvod/video/delete-batch")
    public R removeBatch(@RequestParam("videoIdList") List<String> videoIdList);

}
