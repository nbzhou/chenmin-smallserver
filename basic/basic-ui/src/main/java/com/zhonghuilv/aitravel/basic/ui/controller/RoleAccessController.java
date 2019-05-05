package com.zhonghuilv.aitravel.basic.ui.controller;

import com.zhonghuilv.aitravel.BasicUIApplication;
import com.zhonghuilv.aitravel.basic.intf.message.RoleProcessor;
import com.zhonghuilv.aitravel.basic.intf.message.RoleSource;
import com.zhonghuilv.aitravel.basic.intf.pojo.vo.RoleAccessVO;
import com.zhonghuilv.aitravel.common.ui.controller.BasicUIController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.common.pojo.dto.PageQuery;
import com.zhonghuilv.aitravel.basic.intf.clients.RoleAccessClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.RoleAccess;

import io.swagger.annotations.*;

import java.util.List;

/**
 * Created by zhengjing  on 2018-03-27 17:47:11
 */
@RestController
@RequestMapping("/role_access")
@Api(value = "RoleAccessController", description = "角色权限")
@EnableBinding(RoleSource.class)
public class RoleAccessController{

    private RoleAccessClient roleAccessClient;

    @Autowired
    private RoleSource roleSource;

    @Autowired
    public RoleAccessController(RoleAccessClient roleAccessClient){
        this.roleAccessClient = roleAccessClient;
    }


    @ApiOperation("新增")
    @RequestMapping(value = "/mysave", method = RequestMethod.POST)
    public ApiResult<Boolean> mySave(@RequestBody RoleAccess model) {

        ApiResult<Boolean> result = ApiResult.success(roleAccessClient.mySave(model));
        roleSource.output().send(MessageBuilder.withPayload("roleChange").build());
        return result;
    }

    //查询
    @ApiOperation("主键查询")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResult<RoleAccessVO> loadById(@PathVariable("id") Long id) {
        return ApiResult.success(roleAccessClient.loadById(id));
    }
}

