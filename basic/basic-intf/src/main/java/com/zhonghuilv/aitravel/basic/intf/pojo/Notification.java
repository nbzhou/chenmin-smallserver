
package com.zhonghuilv.aitravel.basic.intf.pojo;

import com.zhonghuilv.aitravel.common.pojo.po.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * TODO 是否需要做推送功能
 * Created by zhengjing  on 2018-04-29 17:12:50
 */
@ApiModel("系统通知")
@Table(name = "sys_notification")
@Data
public class Notification extends MainPO {

    @Id
    @OrderBy("desc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ApiModelProperty(value = "用户id", required = true)
    private Long userId;

    @ApiModelProperty(value = "通知内容html", required = true)
    private String content;

    @ApiModelProperty(value = "通知类型   GET_COUPON(\"获得优惠券\"),\n" +
            "    EXPIRE_COUPON(\"优惠券过期\"),\n" +
            "    GET_INTEGRAL(\"获得积分\")")
    private String type;

    @ApiModelProperty(value = "通知时间")
    private java.time.LocalDateTime notifyTime;

    @ApiModelProperty("优惠券就是优惠券id 积分就是具体积分值")
    private Long targetValue;

    @ApiModelProperty(value = "状态 1-未读,2-已读")
    private Integer state;

}

