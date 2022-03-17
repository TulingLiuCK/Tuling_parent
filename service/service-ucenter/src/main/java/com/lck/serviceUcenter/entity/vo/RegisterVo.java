package com.lck.serviceUcenter.entity.vo;

import lombok.Data;

/***
 #Create by LCK on 2022/3/12
 # 用法:注册
 */
@Data
public class RegisterVo {
    private String nickname;
    private String mobile;
    private String password;
    private String code;
}
