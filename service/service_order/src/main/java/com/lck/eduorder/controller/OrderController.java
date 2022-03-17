package com.lck.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lck.commonutils.JwtUtils;
import com.lck.commonutils.R;
import com.lck.eduorder.entity.Order;
import com.lck.eduorder.service.OrderService;
import com.lck.entity.CustomerException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author lck
 * @since 2022-03-15
 */
@RestController
@CrossOrigin
@RequestMapping("/eduorder/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    //生成订单的方法
    @SneakyThrows
    @PostMapping("/createOrder/{courseId}")
    public R saveOrder(@PathVariable String courseId, HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //判断是否登录
        if (StringUtils.isEmpty(memberId)){
            throw new CustomerException(20001,"请登录");
        }
        //生成订单
        String orderNo = orderService.createOrders(courseId,memberId);
        return R.ok().data("orderNo", orderNo);
    }
    //根据订单id查询订单信息
    @GetMapping("getOrderInfo/{orderNo}")
    public R getOrderInfo(@PathVariable String orderNo){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        Order order = orderService.getOne(wrapper);
        System.out.println(order+"---------");
        return R.ok().data("item", order);
    }

}

