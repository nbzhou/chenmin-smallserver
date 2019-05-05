package com.zhonghuilv.aitravel.basic.service.controller;

import com.zhonghuilv.aitravel.basic.intf.BasicConstant;
import com.zhonghuilv.aitravel.basic.service.util.FindChildOfAccess;
import com.zhonghuilv.aitravel.common.service.util.ExampleBuiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import com.zhonghuilv.aitravel.common.service.controller.BasicController;
import com.zhonghuilv.aitravel.basic.intf.pojo.SysAccess;
import com.zhonghuilv.aitravel.basic.intf.clients.SysAccessClient;
import com.zhonghuilv.aitravel.basic.service.mapper.SysAccessMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjing  on 2018-03-30 17:27:11
 */
@RestController
@RequestMapping("/sys_access")
@Api(value = "SysAccessController", description = "菜单表")
public class SysAccessController extends BasicController<SysAccess> implements SysAccessClient{

	private SysAccessMapper sysAccessMapper;

    @Autowired
    public SysAccessController(SysAccessMapper sysAccessMapper) {
        super(sysAccessMapper);
        this.sysAccessMapper =sysAccessMapper;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SysAccess loadById(@PathVariable("id") Long id){
            return super.loadById(id);
    }

    @Override
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Long mydelete(@PathVariable("id") Long id){
        FindChildOfAccess findChildOfAccess = new FindChildOfAccess(id,sysAccessMapper);
        delete(id);
        return findChildOfAccess.deleteAllChild();
    }

    @Override
    public String[] getServiceName() {
        return BasicConstant.SERVICE_NAME_ARRAY;
    }
}

