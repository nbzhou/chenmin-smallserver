package com.zhonghuilv.aitravel.basic.api.controller;

import com.zhonghuilv.aitravel.authorization.common.util.UserUtil;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.constant.intf.clients.StsClient;
import com.zhonghuilv.aitravel.constant.intf.pojo.StsCredentialsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * Create By zhengjing on 2018/4/2 09:07
 */
@Api(description = "阿里云STS(Security Token Service)")
@RestController
@RequestMapping("/sts")
public class StsController {

    @Autowired
    StsClient stsClient;

    @ApiOperation("授权上传头像文件夹")
    @GetMapping("/avatar")
    public ApiResult<StsCredentialsVO> policyAvatar() {
        return ApiResult.success(policyDirectory("avatar"));
    }

    @ApiOperation("授权明信片文件夹")
    @GetMapping("/postcard")
    public ApiResult<StsCredentialsVO> policyBiz() {

        return ApiResult.success(policyDirectory("postcard"));
    }

    private StsCredentialsVO policyDirectory(String directory) {
        StsCredentialsVO vo = stsClient.policyBiz(directory, UserUtil.getUserId());
        if (vo != null && StringUtils.isNotBlank(vo.getPath())) {
            if (vo.getPath().startsWith("/")) {
                vo.setPath(vo.getPath().substring(1));
            }
        }
        return vo;
    }
}
