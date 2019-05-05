package com.zhonghuilv.aitravel.basic.ui.controller;

import com.zhonghuilv.aitravel.basic.intf.clients.NotificationClient;
import com.zhonghuilv.aitravel.basic.intf.enums.EnumNotificationType;
import com.zhonghuilv.aitravel.basic.intf.pojo.Notification;
import com.zhonghuilv.aitravel.basic.ui.controller.message.InfoCouponSink;
import com.zhonghuilv.aitravel.order.intf.enums.StatusEnums;
import com.zhonghuilv.aitravel.order.intf.pojo.CouponInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;

import javax.inject.Named;
import java.time.LocalDateTime;

@Named
@Slf4j
@EnableBinding(InfoCouponSink.class)
public class InfoCouponLisenller {

    @Autowired
    private NotificationClient notificationClient;

    @org.springframework.cloud.stream.annotation.StreamListener(InfoCouponSink.INPUT)
    public void process(CouponInfo payMessage) {
        int i = payMessage.getStatus();
        log.info("收到消息");
        Notification info = new Notification();
        info.setType(EnumNotificationType.GET_COUPON.name());
        info.setState(StatusEnums.SUCCESS.getKey());
        info.setUserId(payMessage.getUserId());
        info.setNotifyTime(LocalDateTime.now());
        info.setContent(payMessage.getContext());
        info.setTargetValue(payMessage.getId());
        notificationClient.save(info);
    }
}
