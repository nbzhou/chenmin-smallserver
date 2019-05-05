package com.zhonghuilv.aitravel.basic.intf.clients;

import com.zhonghuilv.aitravel.basic.intf.BasicConstant;
import com.zhonghuilv.aitravel.basic.intf.pojo.RoleAccess;
import com.zhonghuilv.aitravel.basic.intf.pojo.SysAccessRole;
import com.zhonghuilv.aitravel.basic.intf.pojo.vo.RoleAccessVO;
import com.zhonghuilv.aitravel.common.intf.BasicClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhengjing  on 2018-03-27 17:47:11
 */
@FeignClient(value = BasicConstant.SERVICE_NAME)
@RequestMapping("/role_access")
public interface RoleAccessClient {

    /**
     * 新增
     *
     * @return
     */
    @RequestMapping(value = "/mysave", method = RequestMethod.POST)
    Boolean mySave(@RequestBody RoleAccess model);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    RoleAccessVO loadById(@PathVariable("id") Long id);
}

