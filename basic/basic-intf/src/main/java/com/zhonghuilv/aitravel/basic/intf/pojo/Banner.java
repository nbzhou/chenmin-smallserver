
package com.zhonghuilv.aitravel.basic.intf.pojo;

import com.zhonghuilv.aitravel.common.pojo.po.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by zhengjing  on 2018-06-06 10:41:54
 */
@ApiModel("轮播图")
@Table(name = "sys_banner")
@Data
public class Banner extends MainPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "景区ID")
    private Long scenicId;

    @NotNull(message = "轮播编码不能为空")
    @ApiModelProperty(value = "轮播编码   INTEGRAL_MALL(\"积分商城\")")
    private String bannerCode;

    @NotNull(message = "图片地址不能为空")
    @ApiModelProperty(value = "图片地址", required = true)
    private String imgUrl;

    @ApiModelProperty(value = "跳转地址")
    private String path;

    @OrderBy("asc")
    @ApiModelProperty(value = "排序", required = true)
    private Integer orderNo;

}

