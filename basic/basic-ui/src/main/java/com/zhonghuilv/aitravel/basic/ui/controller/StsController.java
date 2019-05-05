package com.zhonghuilv.aitravel.basic.ui.controller;

import com.zhonghuilv.aitravel.authorization.common.util.UserUtil;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.constant.intf.clients.StsClient;
import com.zhonghuilv.aitravel.constant.intf.pojo.StsCredentialsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By zhengjing on 2018/4/2 09:07
 */
@Api(description = "阿里云STS(Security Token Service)")
@RestController
@RequestMapping("/sts")
public class StsController {

    @Autowired
    StsClient stsClient;

    @ApiOperation("授权当前用户一个文件夹")
    @GetMapping("/{biz}")
    public ApiResult<StsCredentialsVO> policyAvatar(@PathVariable("biz") String biz) {
        StsCredentialsVO vo = stsClient.policyBiz(biz, UserUtil.getUserId());
        if (vo != null && StringUtils.isNotBlank(vo.getPath())) {
            if (vo.getPath().startsWith("/")) {
                vo.setPath(vo.getPath().substring(1));
            }
        }
        return ApiResult.success(vo);
    }
}
