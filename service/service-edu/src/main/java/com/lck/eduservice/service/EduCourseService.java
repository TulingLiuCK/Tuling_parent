package com.lck.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lck.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lck.eduservice.entity.frontVo.CourseFrontVo;
import com.lck.eduservice.entity.frontVo.CourseWebVo;
import com.lck.eduservice.entity.vo.CourseInfoVo;
import com.lck.eduservice.entity.vo.CoursePublishVo;
import com.lck.eduservice.entity.vo.CourseQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lck
 * @since 2022-03-06
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id获取课程信息，用于返回上一步数据回显
    CourseInfoVo getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程最终确认
    CoursePublishVo publishCourseInfo(String id);

    //多条件查询带分页
    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    //根据id删除课程   还有课程小面的小节 之类的也要删除
    boolean removeCourse(String id);
    //热门讲师
    List<EduCourse> selectHotCourse();
    //前台页面条件分页查询课程
    Map<String,Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);


    //根据课程id，编写sql语句查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
