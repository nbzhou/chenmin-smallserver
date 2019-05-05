package com.zhonghuilv.aitravel.basic.ui.controller.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InfoCouponSink {
    String INPUT = "info-coupon-input";

    @Input(INPUT)
    SubscribableChannel gameOrderInput();
}
