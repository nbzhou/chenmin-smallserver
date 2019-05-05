
package com.zhonghuilv.aitravel.basic.intf.pojo;

import com.zhonghuilv.aitravel.common.pojo.po.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhengjing  on 2018-05-23 09:22:22
 */
@ApiModel("配置信息")
@Table(name = "sys_config")
@Data
public class Config extends MainPO{

    @Id
	@OrderBy("desc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "景区id")
    private Long scenicId;

    @ApiModelProperty(value = "配置code")
    private String configCode;

    @ApiModelProperty(value = "配置值")
    private String configValue;





}

