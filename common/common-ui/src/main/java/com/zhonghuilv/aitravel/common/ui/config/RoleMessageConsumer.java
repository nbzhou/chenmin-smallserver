package com.zhonghuilv.aitravel.common.ui.config;

import com.zhonghuilv.aitravel.authorization.intf.CustomInvocationSecurityMetadataSource;
import com.zhonghuilv.aitravel.basic.intf.message.RoleSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

/**
 * Create By zhengjing on 2018/5/5 16:15
 */
@EnableBinding(RoleSink.class)
public class RoleMessageConsumer {

    @Autowired
    CustomInvocationSecurityMetadataSource customInvocationSecurityMetadataSource;

    @StreamListener(RoleSink.INPUT)
    public void handle(Message<String> message) {
        customInvocationSecurityMetadataSource.reload();
    }

}
