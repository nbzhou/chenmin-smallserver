package com.zhonghuilv.aitravel.basic.intf.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * Create By zhengjing on 2018/5/23 14:41
 */
@ApiModel("幸运用户查询")
@Data
public class RunDataAwardRecordQueryVO {

    @ApiModelProperty("开始日期")
    private LocalDate startDate;

    @ApiModelProperty("结束日期")
    private LocalDate endDate;

    @ApiModelProperty("景区Id")
    private Long scenicId;
}
