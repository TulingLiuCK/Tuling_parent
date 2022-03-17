package com.lck.serviceUcenter.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lck.commonutils.JwtUtils;
import com.lck.entity.CustomerException;
import com.lck.serviceUcenter.entity.UcenterMember;
import com.lck.serviceUcenter.entity.vo.LoginVo;
import com.lck.serviceUcenter.entity.vo.RegisterVo;
import com.lck.serviceUcenter.mapper.UcenterMemberMapper;
import com.lck.serviceUcenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author lck
 * @since 2022-03-12
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @SneakyThrows
    @Override
    public String login(UcenterMember ucenterMember) {
        String phone = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(phone)){
            throw new CustomerException(20001,"手机号或者密码为空");
        }
        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", phone);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        if (mobileMember==null){
            throw new CustomerException(20001, "手机号不存在");
        }
        //判断用户是否被禁用
        if (mobileMember.getIsDeleted()){
            throw new CustomerException(20001,"请重新注册");
        }
        String md5Password = getMd5Password(password);
        System.out.println(md5Password+"=======================");
        System.out.println(mobileMember.getPassword()+"=======================");
        if (!md5Password.equals(mobileMember.getPassword())){
            throw new CustomerException(20001,"用户密码不正确");
        }

        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;

    }

    @SneakyThrows
    @Override
    public void register(RegisterVo registerVo) {
        //获取前端传来的数据
        String nickname = registerVo.getNickname(); //昵称
        String code = registerVo.getCode(); //验证码
        String mobile = registerVo.getMobile(); //手机号
        String password = registerVo.getPassword(); //密码

        //非空判断
        if (StringUtils.isEmpty(nickname)
                ||StringUtils.isEmpty(code)
                ||StringUtils.isEmpty(mobile)
                ||StringUtils.isEmpty(password)){
            throw new CustomerException(20001,"传来的数据有空值，注册失败");
        }
        if (!code.equals("7777")){
            throw new CustomerException(20001,"验证码不正确，注册失败");
        }
        //手机号不能重复
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count>=1){
            throw new CustomerException(20001,"手机号重复，注册失败");
        }
        UcenterMember member = new UcenterMember();
        String md5Password = getMd5Password(password);
        member.setPassword(md5Password);
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setIsDisabled(false);//用户不禁用
        member.setAvatar("https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg");
        baseMapper.insert(member);
    }

    @Override
    public UcenterMember getMenberByOperid(String openid) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        UcenterMember member = baseMapper.selectOne(queryWrapper);
        return member;
    }

    //根据日期，获取那天注册人数
    @Override
    public Integer getCountRegister(String day) {
        return baseMapper.getCountRegister(day);
    }


    //MD5加密
    private String getMd5Password(String password){
        for (int i = 0; i <3 ; i++) {
            password = DigestUtils.md5DigestAsHex((password).getBytes()).toUpperCase();
        }
        return password;
    }
}
