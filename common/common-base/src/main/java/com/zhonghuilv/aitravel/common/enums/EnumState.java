package com.zhonghuilv.aitravel.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create By zhengjing on 2018/3/28 15:01
 */
@AllArgsConstructor
@Getter
public enum EnumState {

    NORMAL(1,"正常"),
    FORBIDEN(1,"禁用"),
    ;

    private Integer key;

    private String desc;
}
