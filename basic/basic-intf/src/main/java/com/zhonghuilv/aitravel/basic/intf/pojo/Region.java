
package com.zhonghuilv.aitravel.basic.intf.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhengjing  on 2018-05-29 13:02:55
 */
@ApiModel("行政区划")
@Table(name = "region")
@Data
public class Region{

    @Id
	@OrderBy("desc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "行政区划代码", required = true)
    private Integer areaCode;

    @ApiModelProperty(value = "行政区划名称")
    private String areaName;

    @ApiModelProperty(value = "行政区划等级 1省级 2市级 3区县")
    private Integer areaType;

    @ApiModelProperty(value = "上级行政区划代码")
    private Integer parentCode;

}

