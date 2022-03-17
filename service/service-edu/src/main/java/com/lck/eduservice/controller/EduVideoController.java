package com.lck.eduservice.controller;


import com.lck.commonutils.R;
import com.lck.eduservice.client.VodClient;
import com.lck.eduservice.entity.EduVideo;
import com.lck.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author lck
 * @since 2022-03-06
 */
@RestController
@CrossOrigin //跨域
@RequestMapping("/eduservice/edu-video")
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;
    //注入删除小节的方法
    @Autowired
    private VodClient vodClient;


    //添加小节
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return R.ok();
    }
    //删除小节 删除对应的阿里视频
    @DeleteMapping("/deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
        //一定要删除视频id，在删除小节id，若先删除小节id，可能会查不到视频id  ！！！

        //根据小节id获得视频id
        EduVideo eduVideo = eduVideoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();

        if (!StringUtils.isEmpty(videoSourceId)){
            //根据视频id，通过远程调用实现删除
            vodClient.removeAliyunVideoById(videoSourceId); //参数是视频id，而方法中是小节id 所以要通过小节id得到视频id
        }


        eduVideoService.removeById(id);
        return R.ok();
    }
    //修改小节
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return R.ok();
    }

    //根据id查询小节
    @GetMapping("/getVideo/{videoId}")
    public R getVideo(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return R.ok().data("video", eduVideo);
    }

}

