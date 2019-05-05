
package com.zhonghuilv.aitravel.basic.intf.pojo;

import com.zhonghuilv.aitravel.common.pojo.po.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by chenmin  on 2018-05-22 16:51:52
 */
@ApiModel("微信步数奖励配置")
@Data
public class RunDataAwardConfig extends MainPO{

    /**
     * config code
     */
    public static final String CONFIG_CODE = "RUN_DATA_AWARD";
    @Id
	@OrderBy("desc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "景区id")
    private Long scenicId;

    @ApiModelProperty(value = "奖励类型 1-优惠券 2-积分")
    private Integer awardType;

    @ApiModelProperty(value = "奖励优惠券 传优惠券ID 奖励积分 传奖励多少积分")
    private Long awardValue;

}

