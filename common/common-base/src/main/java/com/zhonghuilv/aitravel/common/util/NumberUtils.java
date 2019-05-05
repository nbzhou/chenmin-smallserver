package com.zhonghuilv.aitravel.common.util;

import com.zhonghuilv.aitravel.common.constants.ApplicationConfig;

import java.util.Objects;

/**
 * Create By zhengjing on 2018/2/1 11:27
 */
public class NumberUtils {

    public static boolean needCount(Integer count) {
        return (Objects.nonNull(count) && count.compareTo(ApplicationConfig.INT_ZERO) == 1);
    }

    public static boolean needCount(Long count) {
        return (Objects.nonNull(count) && count.compareTo(ApplicationConfig.LONG_ZERO) == 1);
    }
}
