package com.zhonghuilv.aitravel.common.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

/**
 * LocalDate 全局转换器
 * Create By zhengjing on 2018/5/11 10:09
 */
//@Component
public class LocalDateConverter implements Converter<String, LocalDate> {

    @Nullable
    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source);
    }
}
