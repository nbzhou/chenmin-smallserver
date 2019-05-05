
package com.zhonghuilv.aitravel.basic.intf.pojo;

import com.zhonghuilv.aitravel.common.pojo.po.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhengjing  on 2018-03-27 19:43:45
 */
@ApiModel("用户扩展")
@Table(name = "user_extends")
@Data
public class UserExtends extends MainPO{

    @Id
    @ApiModelProperty(value = "用户ID")
    public java.lang.Long id;

    @ApiModelProperty(value = "用户所属景区id")
    private java.lang.String  scenicId;

}

