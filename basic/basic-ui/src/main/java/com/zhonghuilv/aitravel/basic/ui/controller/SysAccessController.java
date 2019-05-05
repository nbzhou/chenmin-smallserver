package com.zhonghuilv.aitravel.basic.ui.controller;

import com.github.pagehelper.PageInfo;
import com.zhonghuilv.aitravel.basic.intf.BasicConstant;
import com.zhonghuilv.aitravel.basic.intf.clients.SysAccessClient;
import com.zhonghuilv.aitravel.basic.intf.message.RoleSource;
import com.zhonghuilv.aitravel.basic.intf.pojo.SysAccess;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.common.pojo.dto.PageQuery;
import com.zhonghuilv.aitravel.common.ui.controller.BasicUIController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhengjing  on 2018-03-30 17:27:11
 */
@RestController
@RequestMapping("/sys_access")
@Api(value = "SysAccessController", description = "菜单表")
public class SysAccessController  extends BasicUIController<SysAccess>{

    private SysAccessClient SysAccessClient;

    @Autowired
    private RoleSource roleSource;

    @Autowired
    public SysAccessController(SysAccessClient sysAccessClient) {
        super(sysAccessClient);
        this.SysAccessClient = sysAccessClient;
    }

    @ApiOperation("新增")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ApiResult<SysAccess> save(@RequestBody SysAccess model) {
        return ApiResult.success(SysAccessClient.save(model));
    }

    @ApiOperation("全量修改 没传过来的参数会改成null")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ApiResult<SysAccess> update(@RequestBody SysAccess model) {
        roleSource.output().send(MessageBuilder.withPayload("roleChange").build());
        return ApiResult.success(SysAccessClient.update(model));
    }

    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public ApiResult<SysAccess> updateSelective(@RequestBody SysAccess model) {
        roleSource.output().send(MessageBuilder.withPayload("roleChange").build());
        return ApiResult.success(SysAccessClient.updateSelective(model));
    }

    @ApiOperation("物理删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ApiResult<Long> mydelete(@PathVariable("id") Long id) {
        return ApiResult.success(SysAccessClient.mydelete(id));
    }

    //查询
    @ApiOperation("主键查询")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResult<SysAccess> loadById(@PathVariable("id") Long id) {
        return ApiResult.success(SysAccessClient.loadById(id));
    }

    @ApiOperation("单行查询")
    @RequestMapping(value = "/_search_one", method = RequestMethod.POST)
    public ApiResult<SysAccess> loadOne(@RequestBody SysAccess model) {
        return ApiResult.success(SysAccessClient.loadOne(model));
    }

    @RequestMapping(value = "/_search", method = RequestMethod.POST)
    public ApiResult<List<SysAccess>> loadList(@RequestBody SysAccess model) {
        return ApiResult.success(SysAccessClient.loadList(model));
    }

    @ApiOperation("分页查询")
    @RequestMapping(value = "/_search_page", method = RequestMethod.POST)
    public ApiResult<PageInfo<SysAccess>> loadPage(@RequestBody PageQuery<SysAccess> query) {
        return ApiResult.success(SysAccessClient.loadPage(query));
    }

    @ApiOperation("获取所有微服务名")
    @RequestMapping(value = "/_get_service_name",method = RequestMethod.GET)
    public ApiResult<String[]> getServiceName() {
        return ApiResult.success(SysAccessClient.getServiceName());
    }
}

