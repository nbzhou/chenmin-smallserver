package com.zhonghuilv.aitravel.basic.intf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create By zhengjing on 2018/5/23 11:35
 */
@AllArgsConstructor
@Getter
public enum EnumRunDataAwardRecord {

    /**
     * 未发
     */
    NOT_GRANT(1),
    /**
     * 已发
     */
    GRANTED(2);

    private Integer key;

}
