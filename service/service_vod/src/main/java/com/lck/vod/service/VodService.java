package com.lck.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/***
 #Create by LCK on 2022/3/10
 # 用法:
 */
public interface VodService {
    //上传视频
    String uploadVideoAliyun(MultipartFile file);

    //删除视频
    void removeAliyunVideobyId(String id);

    //删除课程下的多个视频
    void removeMoreVideo(List<String> videoIdList);

    String getPlayAuth(String id);
}
