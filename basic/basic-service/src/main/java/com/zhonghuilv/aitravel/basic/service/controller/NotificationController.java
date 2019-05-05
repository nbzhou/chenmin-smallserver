package com.zhonghuilv.aitravel.basic.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import com.zhonghuilv.aitravel.common.service.controller.BasicController;
import com.zhonghuilv.aitravel.basic.intf.pojo.Notification;
import com.zhonghuilv.aitravel.basic.intf.clients.NotificationClient;
import com.zhonghuilv.aitravel.basic.service.mapper.NotificationMapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zhengjing  on 2018-04-29 17:12:50
 */
@RestController
@RequestMapping("/notification")
@Api(value = "NotificationController", description = "系统通知")
public class NotificationController extends BasicController<Notification> implements NotificationClient{

	private NotificationMapper notificationMapper;

    @Autowired
    public NotificationController(NotificationMapper notificationMapper) {
        super(notificationMapper);
        this.notificationMapper =notificationMapper;
    }

}

