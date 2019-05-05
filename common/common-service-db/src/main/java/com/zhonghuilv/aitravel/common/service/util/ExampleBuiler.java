package com.zhonghuilv.aitravel.common.service.util;

import tk.mybatis.mapper.entity.Example;

/**
 * Create By zhengjing on 2018/3/17 10:21
 */
public class ExampleBuiler {

    public static Example cls(Class<?> clazz) {

        return new Example(clazz);
    }
}
