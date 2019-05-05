
package com.zhonghuilv.aitravel.basic.intf.pojo;

import com.zhonghuilv.aitravel.common.pojo.po.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by chenmin  on 2018-05-19 16:54:23
 */
@ApiModel("排名奖励配置")
@Table(name = "rank_award_config")
@Data
public class RankAwardConfig extends MainPO{

    @Id
	@OrderBy("desc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("景区Id")
    private Long scenicId;

    @ApiModelProperty(value = "排名奖励code(RUN_DATA 步数排行 GAME_LINE 游戏排名)")
    private String rankAwardCode;

    @ApiModelProperty(value = "比对方式EQ,LTE,LT（EQ =,LTE <=,LT <）")
    private String calculateWay;

    @ApiModelProperty(value = "计算参数 json array string")
    private String calculateParam;

    @ApiModelProperty(value = "奖励类型 1-优惠券 2-积分")
    private Integer awardType;

    @ApiModelProperty(value = "奖励优惠券 传优惠券ID 奖励积分 传奖励多少积分")
    private String awardValue;





}

