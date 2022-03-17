package com.lck.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lck.commonutils.R;
import com.lck.eduservice.entity.EduCourse;
import com.lck.eduservice.entity.EduTeacher;
import com.lck.eduservice.service.EduCourseService;
import com.lck.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/***
 #Create by LCK on 2022/3/13
 # 用法:
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/teacherFront")
public class TeacherFrontController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduCourseService eduCourseService;

    //前台系统分页查询
    @PostMapping("/getTeacherFrontPageList/{page}/{limit}")
    public R getTeacherFrontPageList(@PathVariable Long page,@PathVariable Long limit){
        Page<EduTeacher> teacherPage= new Page<>(page,limit);
        Map<String,Object> map = eduTeacherService.getTeacherFrontPageList(teacherPage);
        return R.ok().data(map);
    }
    //根据id查询讲师
    @GetMapping("/getTeacherInfo/{teacherId}")
    public R getTeacherInfo(@PathVariable String teacherId){
        EduTeacher teacher = eduTeacherService.getById(teacherId);
        //查询讲师课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", teacherId);
        List<EduCourse> courseList = eduCourseService.list(wrapper);

        return R.ok().data("teacher", teacher).data("courseList",courseList);
    }

}
