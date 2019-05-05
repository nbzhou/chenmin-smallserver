package com.zhonghuilv.aitravel.basic.ui.controller;

import com.zhonghuilv.aitravel.basic.intf.clients.RoleClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.Role;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.common.ui.controller.BasicUIController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by zhengjing  on 2018-03-27 17:47:13
 */
@RestController
@RequestMapping("/sys_role")
@Api(value = "RoleController", description = "角色")
public class RoleController extends BasicUIController<Role> {

    private RoleClient roleClient;

    @Autowired
    public RoleController(RoleClient roleClient) {
        super(roleClient);
        this.roleClient = roleClient;
    }


    @Override
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation("新增角色")
    public ApiResult<Role> save(@RequestBody Role model) {
        return ApiResult.success(roleClient.saveAll(model));
    }

    @Override
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ApiOperation("修改指定字段")
    public ApiResult<Role> updateSelective(@RequestBody Role model) {
        return ApiResult.success(roleClient.updateAllSelective(model));
    }

    @ApiOperation("保存数据权限")
    @RequestMapping(value = "/{roleId}/scenics", method = RequestMethod.POST)
    public ApiResult<Void> saveDataAuthority(@PathVariable("roleId") @ApiParam("角色id") Long roleId,
                                             @RequestBody @ApiParam("景区id集合") List<Long> scenics) {

        roleClient.saveDataAuthority(roleId, scenics);
        return ApiResult.success(null);
    }

    @ApiOperation("获取数据权限")
    @RequestMapping(value = "/{roleId}/scenics", method = RequestMethod.GET)
    public ApiResult<Void> saveDataAuthority(@PathVariable("roleId") @ApiParam("角色id") Long roleId) {

        return ApiResult.success(null);
    }
}

