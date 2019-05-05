package com.zhonghuilv.aitravel.basic.service.controller;

import com.zhonghuilv.aitravel.basic.intf.clients.WechatClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.dto.WechatOpenidDTO;
import com.zhonghuilv.aitravel.basic.intf.pojo.vo.EncryptedData;
import com.zhonghuilv.aitravel.basic.service.util.WechatMiniClient;
import com.zhonghuilv.aitravel.common.util.wechat.WechatCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微信接口 获取session key 解密等
 * Create By zhengjing on 2018/4/28 14:43
 */
@RestController
@Api("微信相关接口")
@RequestMapping("/wechat")
public class WechatController implements WechatClient {


    @Autowired
    WechatMiniClient wechatMiniClient;

    @Override
    @ApiOperation("获取小程序openid sessionKey")
    @RequestMapping(value = "/openid", method = RequestMethod.POST)
    public WechatOpenidDTO openid(@RequestParam("code") String code,
                                  @RequestParam(value = "appid", required = false)
                                          String appid) {

        return wechatMiniClient.jscode2session(code, appid);
    }

    @Override
    @ApiOperation("微信小程序解密")
    @RequestMapping(value = "/decrypt", method = RequestMethod.POST)
    public String decyct(@RequestBody EncryptedData endata) {

        WechatOpenidDTO wechatOpenidDTO = wechatMiniClient.jscode2session(endata.getCode(), endata.getAppid());
        String sessionKey = wechatOpenidDTO.getSession_key();

        return WechatCode.decrypt(endata.getEncryptedData(), sessionKey, endata.getIv());
    }
}
