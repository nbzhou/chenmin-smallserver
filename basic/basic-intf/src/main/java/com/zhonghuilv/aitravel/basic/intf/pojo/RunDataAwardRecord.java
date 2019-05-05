
package com.zhonghuilv.aitravel.basic.intf.pojo;

import com.zhonghuilv.aitravel.common.pojo.po.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by zhengjing  on 2018-05-23 10:28:17
 */
@ApiModel("步数奖励记录 user 不存在时说明没有幸运用户")
@Table(name = "run_data_award_record")
@Data
public class RunDataAwardRecord extends MainPO {

    @Id
    @OrderBy("desc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "景区id")
    private Long scenicId;

    @ApiModelProperty(value = "幸运数字")
    private Integer luckyNumber;

    @ApiModelProperty(value = "奖励日期")
    private LocalDate awardDate;

    @ApiModelProperty(value = "用户id ")
    private Long userId;

    @ApiModelProperty(value = "奖励类型 1-优惠券 2-积分")
    private Integer awardType;

    @ApiModelProperty(value = "奖励优惠券 传优惠券ID 奖励积分 传奖励多少积分")
    private Long awardValue;

    @ApiModelProperty(value = "1-奖励未发 2-奖励已发")
    private Integer state;

    @Transient
    @ApiModelProperty("用户信息")
    private User user;

}

