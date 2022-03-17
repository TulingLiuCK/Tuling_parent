package com.lck.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.lck.msmservice.service.MsmService;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/***
 #Create by LCK on 2022/3/12
 # 用法:
 */
@Service
public class MsmServiceImpl implements MsmService {


    @Override
    public boolean sendMsm(HashMap<String, Object> map, String phone) {
        return false;
    }
}