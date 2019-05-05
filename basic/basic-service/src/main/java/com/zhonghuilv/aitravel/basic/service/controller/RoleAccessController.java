package com.zhonghuilv.aitravel.basic.service.controller;

import com.zhonghuilv.aitravel.basic.intf.clients.RoleAccessClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.RoleAccess;
import com.zhonghuilv.aitravel.basic.intf.pojo.SysAccessRole;
import com.zhonghuilv.aitravel.basic.intf.pojo.vo.RoleAccessVO;
import com.zhonghuilv.aitravel.basic.service.mapper.SysAccessRoleMapper;
import com.zhonghuilv.aitravel.basic.service.service.OldAccessAdapterService;
import com.zhonghuilv.aitravel.common.service.controller.BasicController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhengjing  on 2018-03-27 17:47:11
 */
@RestController
@RequestMapping("/role_access")
@Api(value = "RoleAccessController", description = "角色权限")
public class RoleAccessController implements RoleAccessClient {

    @Autowired
    OldAccessAdapterService oldAccessAdapterService;

    SysAccessRoleMapper sysAccessRoleMapper;

    @Autowired
    public RoleAccessController(SysAccessRoleMapper sysAccessRoleMapper) {
        this.sysAccessRoleMapper = sysAccessRoleMapper;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RoleAccessVO loadById(@PathVariable("id") Long id) {
        return oldAccessAdapterService.select(id);
    }

    @Override
    @ApiOperation("新增")
    @RequestMapping(value = "/mysave", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public Boolean mySave(@RequestBody RoleAccess model) {
        oldAccessAdapterService.delete(model.getId());
        return oldAccessAdapterService.insert(model);
    }
}

