package com.lck.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

/***
 #Create by LCK on 2022/3/10
 # 用法: 课程最终发布pojo
 */
@Data
public class CoursePublishVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;//课程id

    private String title; //课程名称

    private String cover; //封面

    private Integer lessonNum;//课时数

    private String subjectLevelOne;//一级分类

    private String subjectLevelTwo;//二级分类

    private String teacherName;//讲师名称

    private String price;//价格 ，只用于显示
}
