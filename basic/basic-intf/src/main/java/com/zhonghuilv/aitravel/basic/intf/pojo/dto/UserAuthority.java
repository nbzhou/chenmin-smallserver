package com.zhonghuilv.aitravel.basic.intf.pojo.dto;

import com.zhonghuilv.aitravel.basic.intf.pojo.Role;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * Create By zhengjing on 2018/5/4 14:23
 */
@ApiModel("用户权限")
@Data
public class UserAuthority {

    private List<Role> roles;

    private List<Long> scenics;
}
