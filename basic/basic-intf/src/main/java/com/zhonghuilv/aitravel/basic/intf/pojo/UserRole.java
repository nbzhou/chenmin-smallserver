
package com.zhonghuilv.aitravel.basic.intf.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;

/**
 * Created by zhengjing  on 2018-03-27 19:43:44
 */
@ApiModel("用户角色")
@Table(name = "user_role")
@Data
public class UserRole {


    @ApiModelProperty(value = "角色ID")
    public java.lang.String roleId;

    @ApiModelProperty(value = "用户ID")
    public java.lang.Long id;

}

