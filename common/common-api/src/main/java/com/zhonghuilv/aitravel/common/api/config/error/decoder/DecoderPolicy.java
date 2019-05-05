package com.zhonghuilv.aitravel.common.api.config.error.decoder;

/**
 * Create By zhengjing on 2018/4/7 09:35
 */
public interface DecoderPolicy {

    Exception error(String str);
}
