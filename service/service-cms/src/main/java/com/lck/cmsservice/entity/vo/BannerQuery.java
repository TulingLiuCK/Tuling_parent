package com.lck.cmsservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

/***
 #Create by LCK on 2022/3/12
 # 用法: 分页条件包装类
 */
@Data
public class BannerQuery implements Serializable {
    private static  final long serialVersionUID = 1L;

    //幻灯片名字
    private String name;

    private String begin;

    private String end;
}
