package com.chemin.smallserver.constant.service.controller;

import com.chemin.smallserver.constant.intf.clients.StsClient;
import com.chemin.smallserver.constant.intf.pojo.StsCredentialsVO;
import com.chemin.smallserver.constant.service.service.StsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By zhengjing on 2017/12/18 10:44
 */
@RestController
@Api(description = "阿里云STS")
public class StsController implements StsClient {

    @Autowired
    StsService stsService;

    @Override
    public StsCredentialsVO policyBiz(@RequestParam("biz") String biz, @RequestParam("userId") Long userId) {
        String path = "/".concat(biz).concat("/").concat(String.valueOf(userId));
        return stsService.policyDefaultBucket(path, "uid" + userId);
    }

    @Override
    public StsCredentialsVO policyPath(@RequestParam("path") String path) {
        return stsService.policyDefaultBucket(path, "uid0");
    }


}
