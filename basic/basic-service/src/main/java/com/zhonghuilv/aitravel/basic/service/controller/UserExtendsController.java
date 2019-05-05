package com.zhonghuilv.aitravel.basic.service.controller;

import com.zhonghuilv.aitravel.basic.intf.clients.UserExtendsClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.UserExtends;
import com.zhonghuilv.aitravel.basic.service.mapper.UserExtendsMapper;
import com.zhonghuilv.aitravel.common.service.controller.BasicController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengjing  on 2018-03-27 17:47:13
 */
@RestController
@RequestMapping("/user_extends")
@Api(value = "UserExtendsController", description = "用户扩展")
public class UserExtendsController extends BasicController<UserExtends> implements UserExtendsClient {

    private UserExtendsMapper userExtendsMapper;

    @Autowired
    public UserExtendsController(UserExtendsMapper userExtendsMapper) {
        super(userExtendsMapper);
        this.userExtendsMapper = userExtendsMapper;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserExtends loadById(@PathVariable("id") Long id) {
        return super.loadById(id);
    }

}

