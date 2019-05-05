
package com.zhonghuilv.aitravel.basic.intf.pojo;

import com.zhonghuilv.aitravel.common.pojo.po.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhengjing  on 2018-03-27 19:43:45
 */
@ApiModel("角色")
@Table(name = "sys_role")
@Data
public class Role extends MainPO{

    @Id
	@OrderBy("desc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public java.lang.Long id;

    @ApiModelProperty(value = "角色名")
    private java.lang.String roleName;

    @ApiModelProperty(value = "角色编码")
    private java.lang.String roleCode;

    @Transient
    @ApiModelProperty(value = "菜单ID组")
    public List<Long> scenics;


}

