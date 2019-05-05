package com.zhonghuilv.aitravel.basic.intf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create By zhengjing on 2017/12/29 14:53
 */
@AllArgsConstructor
@Getter
public enum EnumUserType {

    APP(1, "手机用户"),
    ADMIN(2, "后台用户");

    private Integer key;

    private String desc;
}
