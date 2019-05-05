package com.zhonghuilv.aitravel.basic.ui.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhonghuilv.aitravel.authorization.common.util.UserUtil;
import com.zhonghuilv.aitravel.basic.intf.clients.UserClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.User;
import com.zhonghuilv.aitravel.basic.intf.pojo.vo.UserVO;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.common.pojo.dto.PageQuery;
import com.zhonghuilv.aitravel.common.pojo.dto.QueryExample;
import com.zhonghuilv.aitravel.common.ui.controller.BasicUIController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value = "UserController", description = "用户数据")
public class UserController extends BasicUIController<User>{
    private UserClient userClient;

    @Autowired
    public UserController(UserClient userClient) {
        super(userClient);
        this.userClient = userClient;
    }

    @ApiOperation("删除")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ApiResult<Boolean> delete(@PathVariable("id") Long id) {
        return ApiResult.success(userClient.delete(id));
    }

    @ApiOperation("单行查询")
    @RequestMapping(value = "/_search_one", method = RequestMethod.POST)
    public ApiResult<User> loadOne(@RequestBody User model) {
        return ApiResult.success(userClient.loadOne(model));
    }

    @RequestMapping(value = "/_search", method = RequestMethod.GET)
    public ApiResult<List<User>> loadList(@RequestBody User model) {
        return ApiResult.success(userClient.loadList(model));
    }

    @ApiOperation("分页查询")
    @RequestMapping(value = "/_search_page", method = RequestMethod.POST)
    public ApiResult<PageInfo<User>> loadPage(@RequestBody PageQuery<User> query) {
        return ApiResult.success(userClient.loadPage(query));
    }

    @ApiOperation("分页查询用户所有数据")
    @RequestMapping(value = "/search_user_info", method = RequestMethod.POST)
    public ApiResult<PageInfo<UserVO>> loadVoPage(@RequestBody PageQuery<User> query) {
        return ApiResult.success(userClient.loadVoPage(query));
    }

    @ApiOperation("部分修改用户所有数据")
    @RequestMapping(value = "/_update", method = RequestMethod.PATCH)
    public ApiResult<UserVO> updateVOSelective(@RequestBody User user) {
        UserVO vo = userClient.updateVOSelective(user);
        if (vo == null) {
            return ApiResult.error("修改失败");
        }
        return ApiResult.success(vo);
    }

    @ApiOperation("获取当前用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public ApiResult<UserVO> getUserInfo() {
        return ApiResult.success(userClient.getUserInfo(UserUtil.getUserId()));
    }

    @ApiOperation("添加新用户")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResult<UserVO> mySave(@RequestBody User model) {
        return ApiResult.success(userClient.mySave(model));
    }
}
