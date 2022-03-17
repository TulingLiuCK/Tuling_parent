package com.lck.eduservice.controller;


import com.lck.commonutils.R;
import com.lck.eduservice.entity.subject.OneSubject;
import com.lck.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author lck
 * @since 2022-03-06
 */
@RestController
@CrossOrigin //解决跨域问题
@RequestMapping("/eduservice/edu-subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类
    //获取上传过来的文件，把文件内容读取出来
    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file){
        eduSubjectService.saveSubject(file,eduSubjectService);
        return R.ok();
    }
    //课程分类列表
    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list=eduSubjectService.getAllOneTwoSubject();
        return  R.ok().data("list",list);
    }

}

