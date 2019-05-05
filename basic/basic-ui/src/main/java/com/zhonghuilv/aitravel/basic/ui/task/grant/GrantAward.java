package com.zhonghuilv.aitravel.basic.ui.task.grant;

import com.zhonghuilv.aitravel.basic.intf.pojo.RunDataAwardConfig;
import com.zhonghuilv.aitravel.basic.ui.dto.Rank;
import com.zhonghuilv.aitravel.travel.intf.pojo.Scenic;

/**
 * Create By zhengjing on 2018/5/21 08:41
 */
public interface GrantAward {

    Long grant(Scenic scenic, RunDataAwardConfig runDataAwardConfig, Rank user);

    boolean supports(Integer grantType);
}
