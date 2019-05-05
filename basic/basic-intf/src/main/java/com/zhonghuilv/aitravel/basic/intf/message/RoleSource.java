package com.zhonghuilv.aitravel.basic.intf.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Create By zhengjing on 2018/5/5 16:03
 */
public interface RoleSource {

    String OUTPUT = "role-source";

    @Output(RoleSource.OUTPUT)
    MessageChannel output();
}
