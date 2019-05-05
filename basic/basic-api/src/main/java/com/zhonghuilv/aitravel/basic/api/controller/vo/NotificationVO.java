package com.zhonghuilv.aitravel.basic.api.controller.vo;

import com.zhonghuilv.aitravel.basic.intf.pojo.Notification;
import com.zhonghuilv.aitravel.order.intf.pojo.TbCoupon;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Create By zhengjing on 2018/5/24 17:07
 */
@Data
@ApiModel("系统通知VO")
public class NotificationVO extends Notification {

    @ApiModelProperty("优惠券信息")
    private TbCoupon tbCoupon;
}
