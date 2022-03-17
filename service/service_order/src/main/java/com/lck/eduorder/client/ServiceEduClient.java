package com.lck.eduorder.client;

import com.lck.commonutils.EduCourseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/***
 #Create by LCK on 2022/3/15
 # 用法:
 */
@Component
@FeignClient(name = "service-edu")
public interface ServiceEduClient {
    //根据课程id，获取课程信息
    @PostMapping("/eduservice/edu-course/getCourseInfoByIdOrder/{id}")
    public EduCourseVo getCourseInfoByIdOrder(@PathVariable("id") String id);
}
