package com.zhonghuilv.aitravel.basic.intf.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;

@ApiModel("角色权限关系表")
@Table(name = "sys_access_role")
@Data
public class SysAccessRole {
    @ApiModelProperty(value = "角色ID")
    public java.lang.Long roleId;

    @ApiModelProperty(value = "权限ID")
    public java.lang.Long accessId;
}
