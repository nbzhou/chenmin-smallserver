package com.zhonghuilv.aitravel.basic.api.controller.vo;

import com.zhonghuilv.aitravel.basic.intf.pojo.RunDataAwardRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Create By zhengjing on 2018/5/23 16:18
 */
@Data
@ApiModel("今日幸运数字和昨日幸运用户")
public class NumberAndDog {

    @ApiModelProperty("今日幸运数字")
    private Integer luckyNumber;

    @ApiModelProperty("user 不存在时说明没有幸运用户")
    private RunDataAwardRecord yesterdayLuckyDog;
}
