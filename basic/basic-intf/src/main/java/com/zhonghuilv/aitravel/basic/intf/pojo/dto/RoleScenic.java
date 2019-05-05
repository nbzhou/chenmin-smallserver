package com.zhonghuilv.aitravel.basic.intf.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Create By zhengjing on 2018/5/4 10:29
 */
@Data
@ApiModel("角色数据权限")
public class RoleScenic {

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("景区id")
    List<Long> scenicId;
}
