package com.chemin.smallserver.constant.intf.clients;

import com.chemin.smallserver.constant.intf.ConstantConstant;
import com.chemin.smallserver.constant.intf.pojo.StsCredentialsVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Create By zhengjing on 2017/12/18 10:42
 */
@FeignClient(value = ConstantConstant.SERVICE_NAME)
@RequestMapping("/sts")
public interface StsClient {

    @RequestMapping(value ="/policy/biz", method = RequestMethod.GET)
    @ApiOperation("授权一个业务")
    StsCredentialsVO policyBiz(@RequestParam("biz") String biz, @RequestParam("userId") Long userId);

    @RequestMapping(value = "/policy/path", method = RequestMethod.GET)
    @ApiOperation("授权一个目录")
    StsCredentialsVO policyPath(@RequestParam("path") String path);
}
