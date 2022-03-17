package com.lck.eduservice.controller;

import com.lck.commonutils.R;
import org.springframework.web.bind.annotation.*;

/***
 #Create by LCK on 2022/3/5
 # 用法: 登录
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin //跨域
public class EduLoginController {
    //login
    @PostMapping("login")
    public R login(){
        return R.ok().data("token", "admin");
    }
    //info
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://c-ssl.duitang.com/uploads/item/201809/12/20180912181003_WUXRw.jpeg");
    }
}
