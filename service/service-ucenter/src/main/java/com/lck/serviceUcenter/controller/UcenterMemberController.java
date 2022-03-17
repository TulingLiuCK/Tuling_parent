package com.lck.serviceUcenter.controller;


import com.lck.commonutils.JwtUtils;
import com.lck.commonutils.R;
import com.lck.commonutils.UcenterMemberVo;
import com.lck.serviceUcenter.entity.UcenterMember;
import com.lck.serviceUcenter.entity.vo.LoginVo;
import com.lck.serviceUcenter.entity.vo.RegisterVo;
import com.lck.serviceUcenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author lck
 * @since 2022-03-12
 */
@RestController
@CrossOrigin
@RequestMapping("/serviceUcenter/ucenter-member")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;

    //登录
    @PostMapping("/login")
    public R login(@RequestBody UcenterMember ucenterMember){
        String token = ucenterMemberService.login(ucenterMember);
        return R.ok().data("token", token);
    }
    //注册
    @PostMapping("/register")
    public R register(@RequestBody(required = false) RegisterVo registerVo){
        ucenterMemberService.register(registerVo);
        return R.ok();
    }

    //根据token获取用户信息
    @GetMapping("/getUserInfoForJwt")
    public R getUserInfoForJwt(HttpServletRequest request){
        //调用jwt工具类里面的根据request对象，获取头信息，返回用户id
        String id = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库，根据用户id，获取用户信息
        UcenterMember member = ucenterMemberService.getById(id);

        return R.ok().data("userInfo",member);
    }
    //根据用户id查询用户信息
    @PostMapping("/getMemberInfoById/{id}")
    public UcenterMemberVo getMemberInfoById(@PathVariable String id){
        UcenterMember member = ucenterMemberService.getById(id);
        UcenterMemberVo ucenterMemberVo = new UcenterMemberVo();
        BeanUtils.copyProperties(member,ucenterMemberVo);
        return ucenterMemberVo;
    }
    //根据日期，获取注册人数
    @GetMapping("/countRegistor/{day}")
    public R countRegister(@PathVariable String day){
        Integer count = ucenterMemberService.getCountRegister(day);
        return R.ok().data("countRegister", count);
    }



}

