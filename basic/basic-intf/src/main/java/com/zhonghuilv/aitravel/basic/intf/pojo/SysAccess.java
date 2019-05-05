
package com.zhonghuilv.aitravel.basic.intf.pojo;

import com.zhonghuilv.aitravel.common.pojo.po.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhengjing  on 2018-03-30 17:27:11
 */
@ApiModel("菜单表")
@Table(name = "sys_access")
@Data
public class SysAccess extends MainPO{

    @Id
	@OrderBy("desc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "菜单ID")
    public java.lang.Long id;

    @ApiModelProperty(value = "父菜单，0代表根菜单")
    private java.lang.Long accessPid;

    @ApiModelProperty(value = "权限名称")
    private java.lang.String accessName;

    @ApiModelProperty(value = "权限所属服务，每个权限只能对应一个服务，如果有多条服务，则将权限拆分")
    private java.lang.String service;

    @ApiModelProperty(value = "菜单地址")
    private java.lang.String accessAdress;

    @ApiModelProperty(value = "权限拥有资源")
    private java.lang.String accessResources;
}

