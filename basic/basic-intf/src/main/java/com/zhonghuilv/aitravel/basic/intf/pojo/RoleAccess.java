
package com.zhonghuilv.aitravel.basic.intf.pojo;

import com.zhonghuilv.aitravel.common.pojo.po.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Created by zhengjing  on 2018-03-27 19:43:44
 */
@ApiModel("角色权限")
@Table(name = "role_access")
@Data
public class RoleAccess extends MainPO{

	@OrderBy("desc")
    @ApiModelProperty(value = "角色ID")
    public java.lang.Long id;

	@OrderBy("desc")
    @ApiModelProperty(value = "菜单ID组")
    public java.lang.String accessId;
}

