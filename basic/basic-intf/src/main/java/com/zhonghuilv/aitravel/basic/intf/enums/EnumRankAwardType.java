package com.zhonghuilv.aitravel.basic.intf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create By zhengjing on 2018/5/19 16:58
 */
@AllArgsConstructor
@Getter
public enum EnumRankAwardType {

    COUPON(1,"优惠券"),
    INTEGRAL(2,"积分");

    private Integer key;
    private String desc;
}
