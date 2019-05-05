package com.zhonghuilv.aitravel.basic.intf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create By zhengjing on 2017/12/21 11:07
 */
@AllArgsConstructor
@Getter
public enum EnumState {
    FORBIDDEN(0, "禁用"),
    NORMAL(1, "正常");

    private Integer state;

    private String desc;
}
