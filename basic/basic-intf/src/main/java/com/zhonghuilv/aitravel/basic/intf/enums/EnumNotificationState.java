package com.zhonghuilv.aitravel.basic.intf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create By zhengjing on 2018/5/3 08:51
 */
@AllArgsConstructor
@Getter
public enum EnumNotificationState {

    NOT_READ(1, "未读"),
    READED(2, "已读");
    private Integer state;
    private String desc;
}
