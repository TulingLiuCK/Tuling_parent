package com.lck.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lck.commonutils.R;
import com.lck.eduservice.entity.EduCourse;
import com.lck.eduservice.entity.chapter.ChapterVo;
import com.lck.eduservice.entity.chapter.VideoVo;
import com.lck.eduservice.entity.frontVo.CourseFrontVo;
import com.lck.eduservice.entity.frontVo.CourseWebVo;
import com.lck.eduservice.service.EduChapterService;
import com.lck.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    //条件查询带分页
    @PostMapping("/getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable Long page, @PathVariable Long limit, @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = eduCourseService.getCourseFrontList(pageCourse,courseFrontVo);
        return R.ok().data(map);
    }
    //查询课程详情
    @ApiOperation(value = "查询课程信息")
    @GetMapping("/getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId){
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(courseId);
        //根据课程id，查询章节和小节
        List<ChapterVo> chapterVideoByCourseId = eduChapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("courseWebVo", courseWebVo).data("chapterVodeoList", chapterVideoByCourseId);
    }




}
