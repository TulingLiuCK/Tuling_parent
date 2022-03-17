package com.lck.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lck.eduservice.entity.EduCourse;
import com.lck.eduservice.entity.EduCourseDescription;
import com.lck.eduservice.entity.frontVo.CourseFrontVo;
import com.lck.eduservice.entity.frontVo.CourseWebVo;
import com.lck.eduservice.entity.vo.CourseInfoVo;
import com.lck.eduservice.entity.vo.CoursePublishVo;
import com.lck.eduservice.entity.vo.CourseQuery;
import com.lck.eduservice.mapper.EduCourseMapper;
import com.lck.eduservice.service.EduChapterService;
import com.lck.eduservice.service.EduCourseDescriptionService;
import com.lck.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lck.eduservice.service.EduVideoService;
import com.lck.entity.CustomerException;
import com.lck.exceptionhandler.GlobalExceptionHandler;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lck
 * @since 2022-03-06
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //课程描述的注入
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;


    @SneakyThrows
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1向课程条添加课程基本信息
        //CourseInfoVo转换为eduCourse
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert<=0){
            throw new CustomerException(20001, "添加课程信息失败");
        }
        //获取添加之后的课程id
        String id = eduCourse.getId();

        //向课程简介表添加课程简介
        //edu_course_description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());

        //手动设置描述课程表的id，与上面的课程信息表id关联
        courseDescription.setId(id);

        eduCourseDescriptionService.save(courseDescription);

        return id;

    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        CourseInfoVo courseInfoVo = new CourseInfoVo();

        //1先查询课程描述表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        //查询描述表
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }


    //修改课程信息
    @SneakyThrows
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int updata = baseMapper.updateById(eduCourse);
        if (updata==0){
            throw new CustomerException(20001, "课程信息修改失败");
        }
        //修改课程描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //取出值，判断是否有数据
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        if (!StringUtils.isEmpty(title)){
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)){
            wrapper.eq("status", status);
        }
        wrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(pageParam,wrapper);
    }

    @SneakyThrows
    @Override
    public boolean removeCourse(String id) {
        //根据课程id删除小节
        eduVideoService.removeVideoByCourseId(id);
        //根据课程id删除章节
        eduChapterService.removeChapterByCourseId(id);
        //根据课程id删除课程描述
        eduCourseDescriptionService.removeById(id);
        //根据课程id删除课程本身
        int result = baseMapper.deleteById(id);
        if (result>=1){
            return true;
        }else {
            throw new CustomerException(20001,"删除失败");
        }

    }
    //查询8个热门课程
    @Override
    @Cacheable(key = "'selectHotCourse'",value = "course")
    public List<EduCourse> selectHotCourse() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("view_count");
        wrapper.last("limit 8");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        //判断条件是否为空
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())){//一级分类
            queryWrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectId())){//二级分类
            queryWrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) {//销量排序
            queryWrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) {//时间排序
            queryWrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {//价格排序
            queryWrapper.orderByDesc("price");
        }

        //封装到page里面
        baseMapper.selectPage(pageCourse, queryWrapper);

        long total = pageCourse.getTotal();//总记录数
        List<EduCourse> courseList = pageCourse.getRecords();//数据集合
        long size = pageCourse.getSize();//每页记录数
        long current = pageCourse.getCurrent();//当前页
        long pages = pageCourse.getPages();//总页数
        boolean hasPrevious = pageCourse.hasPrevious();//是否有上一页
        boolean hasNext = pageCourse.hasNext();//是否有下一页

        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("list",courseList);
        map.put("size",size);
        map.put("current",current);
        map.put("pages",pages);
        map.put("hasPrevious",hasPrevious);
        map.put("hasNext",hasNext);

        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }


}
