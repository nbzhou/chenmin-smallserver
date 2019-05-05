package com.zhonghuilv.aitravel.common.excption;

/**
 * Create By zhengjing on 2017/12/11 13:28
 */
public class ServiceLogicException extends CustomRuntimeException {

    public ServiceLogicException(Long errcode, String message) {
        super(errcode, message);
    }

    public ServiceLogicException(String message) {
        super(message);
    }
}
