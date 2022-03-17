package com.lck.staservice.service;

import com.lck.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author lck
 * @since 2022-03-17
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void createStatisticsByDay(String day);

    Map<String, Object> getShowData(String type, String begin, String end);
}
