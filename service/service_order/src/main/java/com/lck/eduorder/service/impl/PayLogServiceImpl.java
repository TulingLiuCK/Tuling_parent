package com.lck.eduorder.service.impl;

import com.lck.eduorder.entity.PayLog;
import com.lck.eduorder.mapper.PayLogMapper;
import com.lck.eduorder.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author lck
 * @since 2022-03-15
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
