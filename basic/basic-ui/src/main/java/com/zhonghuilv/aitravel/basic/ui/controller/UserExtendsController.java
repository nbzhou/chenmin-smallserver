package com.zhonghuilv.aitravel.basic.ui.controller;

import com.zhonghuilv.aitravel.common.ui.controller.BasicUIController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.common.pojo.dto.PageQuery;
import com.zhonghuilv.aitravel.basic.intf.clients.UserExtendsClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.UserExtends;

import io.swagger.annotations.*;

import java.util.List;

/**
 * Created by zhengjing  on 2018-03-27 17:47:13
 */
@RestController
@RequestMapping("/user_extends")
@Api(value = "UserExtendsController", description = "用户扩展")
public class UserExtendsController  extends BasicUIController<UserExtends>{

    
    private UserExtendsClient userExtendsClient;

    @Autowired
    public UserExtendsController(UserExtendsClient userExtendsClient) {
        super(userExtendsClient);
        this.userExtendsClient = userExtendsClient;
    }

    @ApiOperation("新增")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ApiResult<UserExtends> save(@RequestBody UserExtends model) {
        return ApiResult.success(userExtendsClient.save(model));
    }

    @ApiOperation("全量修改 没传过来的参数会改成null")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ApiResult<UserExtends> update(@RequestBody UserExtends model) {

        return ApiResult.success(userExtendsClient.update(model));
    }

    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public ApiResult<UserExtends> updateSelective(@RequestBody UserExtends model) {
        return ApiResult.success(userExtendsClient.updateSelective(model));
    }

    @ApiOperation("物理删除")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ApiResult<Boolean> delete(@PathVariable("id") Long id) {
        return ApiResult.success(userExtendsClient.delete(id));
    }

    //查询
    @ApiOperation("主键查询")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResult<UserExtends> loadById(@PathVariable("id") Long id) {
        return ApiResult.success(userExtendsClient.loadById(id));
    }

    @ApiOperation("单行查询")
    @RequestMapping(value = "/_search_one", method = RequestMethod.POST)
    public ApiResult<UserExtends> loadOne(@RequestBody UserExtends model) {
        return ApiResult.success(userExtendsClient.loadOne(model));
    }

    @RequestMapping(value = "/_search", method = RequestMethod.POST)
    public ApiResult<List<UserExtends>> loadList(@RequestBody UserExtends model) {
        return ApiResult.success(userExtendsClient.loadList(model));
    }

    @ApiOperation("分页查询")
    @RequestMapping(value = "/_search_page", method = RequestMethod.POST)
    public ApiResult<PageInfo<UserExtends>> loadPage(@RequestBody PageQuery<UserExtends> query) {
        return ApiResult.success(userExtendsClient.loadPage(query));
    }
}

