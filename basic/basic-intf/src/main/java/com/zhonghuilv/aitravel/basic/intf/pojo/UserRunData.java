
package com.zhonghuilv.aitravel.basic.intf.pojo;

import com.zhonghuilv.aitravel.common.pojo.po.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhengjing  on 2018-04-03 16:14:22
 */
@ApiModel("用户运动")
@Table(name = "user_run_data")
@Data
public class UserRunData extends MainPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ApiModelProperty(value = "用户id", required = true)
    private Long userId;

    @ApiModelProperty("景区id")
    private Long scenicId;

    @OrderBy("desc")
    @ApiModelProperty(value = "步数")
    private Integer step;

    @ApiModelProperty(value = "记录日期")
    private java.time.LocalDate runDate;

    @Transient
    private String nickname;

    @Transient
    private String avatar;
}

