package com.lck.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/***
 #Create by LCK on 2022/3/10
 # 用法: 查询对象封装
 */
@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
@Data
public class CourseQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程名称，模糊查询")
    private String title;
    @ApiModelProperty(value = "发布状态，Normal已经发布，Draft未发布")
    private String status;
}
