package com.lck.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lck.commonutils.EduCourseVo;
import com.lck.commonutils.R;
import com.lck.eduservice.entity.EduCourse;
import com.lck.eduservice.entity.frontVo.CourseWebVo;
import com.lck.eduservice.entity.vo.CourseInfoVo;
import com.lck.eduservice.entity.vo.CoursePublishVo;
import com.lck.eduservice.entity.vo.CourseQuery;
import com.lck.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lck
 * @since 2022-03-06
 */
@RestController
@RequestMapping("/eduservice/edu-course")
@CrossOrigin //跨域问题
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    //添加课程基本信息
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }
    //根据课程id查询课程基本信息
    @GetMapping("/getCourseInfo/{courseId}")
    public R getCoureseInfoById(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return  R.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("/updataCourseInfo")
    public R updataCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    //根据课程id查询课程最终确认
    @GetMapping("/getPublishCourseInfo/{id}")
    public R getPublicshCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo = eduCourseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }
    //课程最终发布
    @PostMapping("/publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setStatus("Normal");//设置课程发布状态
        eduCourse.setId(id);
        boolean flag = eduCourseService.updateById(eduCourse);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //课程列表
    @ApiOperation(value = "课程列表")
    @GetMapping("/getCourseList")
    public R getCourseList(){
        List<EduCourse> list = eduCourseService.list(null);
        return R.ok().data("list", list);
    }
    //多条件查询带分页
    @ApiOperation(value = "多条件查询带分页")
    @PostMapping("/pageCourseCondition/{page}/{limit}")
    public R pageCourseCondition(@ApiParam(name = "page", value = "当前页码", required = true)@PathVariable Long page,
                                 @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable Long limit,
                                 @RequestBody(required = false) CourseQuery courseQuery){
        //调用分页page对象
        Page<EduCourse> pageParam = new Page<>(page,limit);
        //调用方法实现多条件分页查询
        eduCourseService.pageQuery(pageParam,courseQuery);

        //获取到查询的数据
        List<EduCourse> records = pageParam.getRecords();
        //获取总记录数
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }
    @DeleteMapping("/removeCourseById/{id}")
    public R removeCourseById(@PathVariable String id){
        boolean flag = eduCourseService.removeCourse(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //根据课程id查询课程信息
    @PostMapping("/getCourseInfoByIdOrder/{id}")
    public EduCourseVo getCourseInfoByIdOrder(@PathVariable String id){
        CourseWebVo courseinfo = eduCourseService.getBaseCourseInfo(id);
        EduCourseVo eduCourseVo = new EduCourseVo();
        BeanUtils.copyProperties(courseinfo,eduCourseVo);
        return eduCourseVo;
    }


}

