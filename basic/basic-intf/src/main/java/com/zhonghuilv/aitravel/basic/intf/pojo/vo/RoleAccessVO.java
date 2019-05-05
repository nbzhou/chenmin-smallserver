package com.zhonghuilv.aitravel.basic.intf.pojo.vo;

import com.zhonghuilv.aitravel.basic.intf.pojo.RoleAccess;
import com.zhonghuilv.aitravel.basic.intf.pojo.SysAccess;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.OrderBy;
import java.util.List;

@ApiModel("角色权限VO")
@Data
public class RoleAccessVO extends RoleAccess {
    @OrderBy("desc")
    @ApiModelProperty(value = "菜单组")
    public List<SysAccess> sysAccessList;
}
