package com.lck.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lck.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lck.eduservice.entity.vo.TeacherQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author lck
 * @since 2022-03-03
 */
public interface EduTeacherService extends IService<EduTeacher> {
    //多条件查询讲师带分页
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

    List<EduTeacher> selectHotTeacher();

    //前台系统分页查询讲师
    Map<String, Object> getTeacherFrontPageList(Page<EduTeacher> teacherPage);
}
