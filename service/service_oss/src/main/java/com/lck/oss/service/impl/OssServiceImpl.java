package com.lck.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.lck.oss.service.OssService;
import com.lck.oss.utils.ConstandPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/***
 #Create by LCK on 2022/3/6
 # 用法: 上传头像的实现类 具体参考阿里oss上传头像
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //工具类获取值
        String endPoint = ConstandPropertiesUtils.END_POINT;
        String accessKeyId = ConstandPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstandPropertiesUtils.KEY_SECRET;
        String bucketName = ConstandPropertiesUtils.BUCKET_NAME;

        InputStream inputStream = null;


        try {
            //创建oss实例
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            //获取上传文件的输入流
            inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();

            //在文件名称添加随机值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid+fileName;
            //把文件按照日期分类
            //获取当前日期
            String dataPath = new DateTime().toString("yyyy/MM/dd");

            //拼接日期
            fileName = dataPath+"/"+fileName;

            //调用Oss实例中的方法实现上传
            //参数1： Bucket名称
            //参数2： 上传到oss文件路径和文件名称/aa/bb/1.jpg
            //参数3： 上传文件到输入流
            ossClient.putObject(bucketName, fileName, inputStream);
            //关闭ossClient
            ossClient.shutdown();
            //把上传后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            //https://lck-edu.oss-cn-lck.aliyuncs.com/01.jpg
            String url = "http://" + bucketName + "." + endPoint + "/" + fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
}
