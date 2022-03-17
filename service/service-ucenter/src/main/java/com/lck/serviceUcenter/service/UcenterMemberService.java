package com.lck.serviceUcenter.service;

import com.lck.serviceUcenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lck.serviceUcenter.entity.vo.LoginVo;
import com.lck.serviceUcenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author lck
 * @since 2022-03-12
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember ucenterMember);

    void register(RegisterVo registerVo);

    UcenterMember getMenberByOperid(String openid);

    //根据日期，获取那一填的注册人数
    Integer getCountRegister(String day);
}
