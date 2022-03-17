package com.lck.vod.controller;

import com.lck.commonutils.R;
import com.lck.entity.CustomerException;
import com.lck.vod.service.VodService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Path;
import java.util.List;

/***
 #Create by LCK on 2022/3/10
 # 用法:
 */
@RestController
@CrossOrigin
@RequestMapping("/eduvod/video")
public class VodController {
    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("/uploadAliyunVideo")
    public R uploadAliyunVideo(MultipartFile file){
        //返回上传视频的id
        String videoId = vodService.uploadVideoAliyun(file);
        return R.ok().data("videoId", videoId);
    }

    //删除视频
    @DeleteMapping("/removeAliyunVideoById/{id}")
    public R removeAliyunVideoById(@PathVariable String id){
        vodService.removeAliyunVideobyId(id);
        return R.ok();
    }

    //根据id删除多个阿里视频
    @DeleteMapping("/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList")List<String> videoIdList){
        vodService.removeMoreVideo(videoIdList);
        return R.ok();
    }
    @SneakyThrows
    @GetMapping("/getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id){
        try {
            String palyAuth = vodService.getPlayAuth(id);
            return R.ok().data("PlayAuth",palyAuth);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomerException(20001,"获取视频凭证失败");
        }
    }

}
