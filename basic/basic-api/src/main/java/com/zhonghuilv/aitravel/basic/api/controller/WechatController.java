package com.zhonghuilv.aitravel.basic.api.controller;

import com.zhonghuilv.aitravel.basic.intf.clients.WechatClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.dto.WechatOpenidDTO;
import com.zhonghuilv.aitravel.basic.intf.pojo.vo.EncryptedData;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.common.util.wechat.WechatCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By zhengjing on 2018/4/3 10:40
 */
@RestController
@Api(description = "微信相关")
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    WechatClient wechatClient;

    @ApiOperation("微信小程序解密")
    @PostMapping("/decrypt")
    public ApiResult<String> decyct(@RequestBody EncryptedData endata) {

        WechatOpenidDTO wechatOpenidDTO = wechatClient.openid(endata.getCode(), endata.getAppid());
        String sessionKey = wechatOpenidDTO.getSession_key();
        return ApiResult.success(WechatCode.decrypt(endata.getEncryptedData(), sessionKey, endata.getIv()));
    }
}
