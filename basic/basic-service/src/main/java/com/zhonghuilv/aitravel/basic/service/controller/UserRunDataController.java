package com.zhonghuilv.aitravel.basic.service.controller;

import com.zhonghuilv.aitravel.basic.intf.clients.UserRunDataClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.UserRunData;
import com.zhonghuilv.aitravel.basic.service.mapper.UserRunDataMapper;
import com.zhonghuilv.aitravel.common.service.controller.BasicController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengjing  on 2018-04-03 16:14:22
 */
@RestController
@RequestMapping("/user_run_data")
@Api(value = "UserRunDataController", description = "用户运动")
public class UserRunDataController extends BasicController<UserRunData> implements UserRunDataClient {

    private UserRunDataMapper userRunDataMapper;

    @Autowired
    public UserRunDataController(UserRunDataMapper userRunDataMapper) {
        super(userRunDataMapper);
        this.userRunDataMapper = userRunDataMapper;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserRunData loadById(@PathVariable("id") Long id) {
        return super.loadById(id);
    }

}

