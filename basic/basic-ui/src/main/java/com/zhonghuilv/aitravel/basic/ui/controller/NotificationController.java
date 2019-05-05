package com.zhonghuilv.aitravel.basic.ui.controller;

import com.zhonghuilv.aitravel.basic.intf.clients.NotificationClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import com.zhonghuilv.aitravel.common.ui.controller.BasicUIController;

/**
 * Created by zhengjing  on 2018-05-04 09:51:26
 */
@RestController
@RequestMapping("/notification")
@Api(value = "NotificationController", description = "系统通知")
public class NotificationController extends BasicUIController<Notification>{


	private NotificationClient notificationClient;

    @Autowired
    public NotificationController(NotificationClient notificationClient){
        super(notificationClient);
        this.notificationClient = notificationClient;
    }


}

