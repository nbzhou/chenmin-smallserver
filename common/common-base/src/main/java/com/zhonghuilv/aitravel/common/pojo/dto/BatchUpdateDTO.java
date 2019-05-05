package com.zhonghuilv.aitravel.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Create By zhengjing on 2018/1/6 16:31
 */
@Data
@ApiModel("批处理转换类")
public class BatchUpdateDTO<T> implements Serializable{

    @ApiModelProperty("修改类容")
    private T model;

    @ApiModelProperty("修改主键集")
    private List<Long> ids;
}
