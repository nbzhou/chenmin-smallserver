package com.zhonghuilv.aitravel.authorization.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create By zhengjing on 2018/2/9 16:08
 */
@Getter
@AllArgsConstructor
public enum EnumLoginMode {
    AUTO("自动"),
    HAND("手动");

    private String desc;
}
