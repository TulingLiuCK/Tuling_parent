package com.lck.eduservice.service;

import com.lck.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lck.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author lck
 * @since 2022-03-06
 */
public interface EduSubjectService extends IService<EduSubject> {
    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    //一级分类
    List<OneSubject> getAllOneTwoSubject();
}
