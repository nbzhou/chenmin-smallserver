package com.zhonghuilv.aitravel.common.pojo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;

/**
 * Create By zhengjing on 2018/3/20 10:35
 */
@Data
public class StatePO extends MainPO {

    @Id
    @OrderBy("desc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ApiModelProperty(value = "状态")
    private Integer state;

}
