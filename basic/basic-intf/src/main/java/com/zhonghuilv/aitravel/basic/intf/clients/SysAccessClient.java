package com.zhonghuilv.aitravel.basic.intf.clients;

import com.github.pagehelper.PageInfo;
import com.zhonghuilv.aitravel.basic.intf.BasicConstant;
import com.zhonghuilv.aitravel.basic.intf.pojo.SysAccess;
import com.zhonghuilv.aitravel.common.intf.BasicClient;
import com.zhonghuilv.aitravel.common.pojo.dto.PageQuery;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by zhengjing  on 2018-03-30 17:27:11
 */
@FeignClient(value = BasicConstant.SERVICE_NAME)
@RequestMapping("/sys_access")
public interface SysAccessClient extends BasicClient<SysAccess>{


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    Long mydelete(@PathVariable("id") Long id);

    @ApiOperation("获取所有微服务名")
    @RequestMapping(value = "/_get_service_name",method = RequestMethod.GET)
    String[] getServiceName();
}

