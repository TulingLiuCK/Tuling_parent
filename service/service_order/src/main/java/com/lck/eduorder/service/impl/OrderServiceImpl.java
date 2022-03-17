package com.lck.eduorder.service.impl;

import com.lck.commonutils.EduCourseVo;
import com.lck.commonutils.UcenterMemberVo;
import com.lck.eduorder.client.ServiceEduClient;
import com.lck.eduorder.client.ServiceUcenterClient;
import com.lck.eduorder.entity.Order;
import com.lck.eduorder.mapper.OrderMapper;
import com.lck.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lck.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author lck
 * @since 2022-03-15
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    //远程调用serviceEdu
    @Autowired
    private ServiceEduClient serviceEduClient;
    @Autowired
    private ServiceUcenterClient serviceUcenterClient;

    //生成订单方法
    @Override
    public String createOrders(String courseId, String memberId) {
        //通过远程调用获取到用户信息，和课程信息
        //根据用户id，获取用户信息
        UcenterMemberVo memberInfo = serviceUcenterClient.getMemberInfoById(memberId);

        //根据课程id，获取课程信息
        EduCourseVo courseInfo = serviceEduClient.getCourseInfoByIdOrder(courseId);

        Order tOrder = new Order();
        tOrder.setMobile(memberInfo.getMobile());
        tOrder.setNickname(memberInfo.getNickname());
        tOrder.setMemberId(memberId);
        tOrder.setCourseCover(courseInfo.getCover());
        tOrder.setCourseId(courseId);
        tOrder.setCourseTitle(courseInfo.getTitle());
        tOrder.setTeacherName(courseInfo.getTeacherName());
        tOrder.setTotalFee(courseInfo.getPrice());
        tOrder.setStatus(0);//支付状态：（ 0：已支付，1：未支付 ）
        tOrder.setPayType(1);//支付类型： 1：微信 ， 2：支付宝
        tOrder.setOrderNo(OrderNoUtil.getOrderNo()); //订单号

        //保存订单
        baseMapper.insert(tOrder);

        //返回订单号
        return tOrder.getOrderNo();
    }
}
