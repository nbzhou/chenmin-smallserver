package com.zhonghuilv.aitravel.basic.intf.clients;

import com.zhonghuilv.aitravel.basic.intf.BasicConstant;
import com.zhonghuilv.aitravel.basic.intf.pojo.Notification;
import com.zhonghuilv.aitravel.common.intf.BasicClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhengjing  on 2018-05-04 09:51:26
 */
@FeignClient(value = BasicConstant.SERVICE_NAME)
@RequestMapping("/notification")
public interface NotificationClient extends BasicClient<Notification> {

}

