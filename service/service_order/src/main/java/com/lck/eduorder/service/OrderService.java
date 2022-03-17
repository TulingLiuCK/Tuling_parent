package com.lck.eduorder.service;

import com.lck.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author lck
 * @since 2022-03-15
 */
public interface OrderService extends IService<Order> {

    String createOrders(String courseId, String memberId);
}
