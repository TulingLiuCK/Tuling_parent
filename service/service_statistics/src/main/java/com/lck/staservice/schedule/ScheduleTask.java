package com.lck.staservice.schedule;

import com.lck.staservice.service.StatisticsDailyService;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/***
 #Create by LCK on 2022/3/17
 # 用法: 定时任务配置类
 */
@Component
public class ScheduleTask {
    @Autowired
    private StatisticsDailyService dailyService;

    //在每天凌晨1点执行方法，把前一天的数据查询进行添加
//    @Scheduled(cron = "0 0 1 * * ? ")//指定cron表达式规则
//    public void task02(){
//        dailyService.createStatisticsByDay(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
//    }
}
