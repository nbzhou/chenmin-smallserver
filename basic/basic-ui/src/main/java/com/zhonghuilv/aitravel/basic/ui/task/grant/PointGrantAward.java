package com.zhonghuilv.aitravel.basic.ui.task.grant;

import com.zhonghuilv.aitravel.basic.intf.enums.EnumRankAwardType;
import com.zhonghuilv.aitravel.basic.intf.pojo.RunDataAwardConfig;
import com.zhonghuilv.aitravel.basic.ui.dto.Rank;
import com.zhonghuilv.aitravel.order.intf.clients.IntegralAccountClient;
import com.zhonghuilv.aitravel.travel.intf.pojo.Scenic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * TODO 实现积分奖励发送
 * Create By zhengjing on 2018/5/21 08:42
 */
//@Service
@Slf4j
public class PointGrantAward implements GrantAward {

    private static final Integer AWARD_TYPE = EnumRankAwardType.INTEGRAL.getKey();

    @Autowired
    private IntegralAccountClient integralAccountClient;

    @Override
    public Long grant(Scenic scenic, RunDataAwardConfig runDataAwardConfig, Rank rank) {

        if (Objects.isNull(rank)) {
            log.warn("需要发送人数为空");
            return null;
        }

        log.trace("发送积分奖励：{},{}", rank, runDataAwardConfig.getAwardValue());
        return null;
    }

    @Override
    public boolean supports(Integer grantType) {
        return AWARD_TYPE.equals(grantType);
    }
}
