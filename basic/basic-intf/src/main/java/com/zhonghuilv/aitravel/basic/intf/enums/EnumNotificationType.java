package com.zhonghuilv.aitravel.basic.intf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create By zhengjing on 2018/4/29 16:56
 */
@AllArgsConstructor
@Getter
public enum EnumNotificationType {
    GET_COUPON("获得优惠券"),
    EXPIRE_COUPON("优惠券过期"),
    GET_INTEGRAL("获得积分");
    private String desc;
}
