package com.zhonghuilv.aitravel.basic.ui.service;

import com.zhonghuilv.aitravel.travel.intf.pojo.Scenic;

import java.time.LocalDate;

/**
 * Create By zhengjing on 2018/5/23 15:32
 */
public interface RunDataAwardRecordService {

    /**
     * 发送当天的奖励
     */
    void grantAward();

    /**
     * 发送指定景区 指定日期的奖励
     *
     * @param scenic    景区
     * @param grantDate 日期
     */
    void grantAward(Scenic scenic, LocalDate grantDate);
}
