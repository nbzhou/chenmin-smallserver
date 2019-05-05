package com.zhonghuilv.aitravel.basic.intf.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Create By zhengjing on 2018/3/21 11:12
 */
@Data
@ApiModel("用户名密码登录")
public class LoginDTO {

    @ApiModelProperty("用户名或手机")
    private String username;

    @ApiModelProperty("密码")
    private String password;
}
