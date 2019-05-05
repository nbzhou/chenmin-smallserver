package com.zhonghuilv.aitravel.basic.ui.task;

import com.zhonghuilv.aitravel.basic.ui.service.RunDataAwardRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Create By zhengjing on 2018/5/12 15:09
 */
@Component
@Slf4j
public class ScheduleUserRunDataRank {

    @Autowired
    private RunDataAwardRecordService runDataAwardRecordService;

    /**
     * 每天 18:01:01 指定定时任务 （TODO 分布式任务框架）
     */
    @Scheduled(cron = "1 50 23 * * ?")
    public void execute() {
        log.info("执行每日步数排行发放奖励定时任务");
        runDataAwardRecordService.grantAward();
    }


}
