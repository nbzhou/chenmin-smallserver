package com.zhonghuilv.aitravel.common.excption;

/**
 * Create By zhengjing on 2018/4/8 10:06
 */
public class ServiceUnrealized extends CustomRuntimeException {
    private static final Long ERRCODE_MIN = 4000_000L;

    public ServiceUnrealized(String message) {
        super(ERRCODE_MIN, message);
    }


    public ServiceUnrealized(Long errcode, String message) {
        super(ERRCODE_MIN + errcode, message);
    }
}
