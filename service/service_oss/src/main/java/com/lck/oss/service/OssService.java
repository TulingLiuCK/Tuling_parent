package com.lck.oss.service;

import org.springframework.web.multipart.MultipartFile;

/***
 #Create by LCK on 2022/3/6
 # 用法: 上传头像
 */
public interface OssService {
    //上传头像到OSS
    String uploadFileAvatar(MultipartFile file);
}
