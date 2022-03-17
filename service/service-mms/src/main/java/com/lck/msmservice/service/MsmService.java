package com.lck.msmservice.service;

import java.util.HashMap;

/***
 #Create by LCK on 2022/3/12
 # 用法:
 */
public interface MsmService {
    //发送短信的方法
    boolean sendMsm(HashMap<String, Object> map, String phone);
}
