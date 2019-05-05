package com.zhonghuilv.aitravel.basic.intf.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.SubscribableChannel;

/**
 * Create By zhengjing on 2018/5/5 16:02
 */
public interface RoleSink {

    String INPUT = "role-sink";

    @Input(RoleSink.INPUT)
    SubscribableChannel input();
}
