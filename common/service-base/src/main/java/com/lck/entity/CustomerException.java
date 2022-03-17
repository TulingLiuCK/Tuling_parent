package com.lck.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 #Create by LCK on 2022/3/4
 # 用法:自定义异常类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerException extends Exception {
    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;
}
