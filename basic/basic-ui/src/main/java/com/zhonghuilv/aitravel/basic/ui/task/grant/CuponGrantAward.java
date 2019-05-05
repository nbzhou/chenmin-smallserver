package com.zhonghuilv.aitravel.basic.ui.task.grant;

import com.zhonghuilv.aitravel.basic.intf.enums.EnumRankAwardType;
import com.zhonghuilv.aitravel.basic.intf.pojo.RunDataAwardConfig;
import com.zhonghuilv.aitravel.basic.ui.dto.Rank;
import com.zhonghuilv.aitravel.order.intf.clients.CouponModelClient;
import com.zhonghuilv.aitravel.travel.intf.pojo.Scenic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Create By zhengjing on 2018/5/21 08:42
 */
@Service
@Slf4j
public class CuponGrantAward implements GrantAward {

    private static final Integer AWARD_TYPE = EnumRankAwardType.COUPON.getKey();

    @Autowired
    private CouponModelClient couponModelClient;

    @Override
    public Long grant(Scenic scenic, RunDataAwardConfig config, Rank rank) {

        if (Objects.isNull(rank)) {
            log.warn("需要发送人数为空");
            return null;
        }

        final Long awardValue = Long.valueOf(config.getAwardValue());

        log.trace("发送优惠券奖励：{},{}", rank, awardValue);
        return couponModelClient.giveCoupon(rank.getUserId(), awardValue, rank.getRank()).getId();
    }

    @Override
    public boolean supports(Integer grantType) {
        return AWARD_TYPE.equals(grantType);
    }
}
