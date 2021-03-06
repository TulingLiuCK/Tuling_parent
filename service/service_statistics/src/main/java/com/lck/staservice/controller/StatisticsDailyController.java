package com.lck.staservice.controller;


import com.lck.commonutils.R;
import com.lck.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author lck
 * @since 2022-03-17
 */
@RestController
@CrossOrigin
@RequestMapping("/staservice/statistics-daily")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    //统计某一天注册人数
    @PostMapping("/registerCount/{day}")
    public R createStatisticsByDay(@PathVariable String day){
        statisticsDailyService.createStatisticsByDay(day);
        return R.ok();
    }

    //图像显示 返回两部分数据，日期json数组，数量json数组
    @GetMapping("/showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,@PathVariable String begin,@PathVariable String end){
        Map<String,Object>  map  =statisticsDailyService.getShowData(type,begin,end);
        return R.ok().data(map);
    }

}

