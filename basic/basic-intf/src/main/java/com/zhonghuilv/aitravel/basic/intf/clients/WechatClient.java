package com.zhonghuilv.aitravel.basic.intf.clients;

import com.zhonghuilv.aitravel.basic.intf.BasicConstant;
import com.zhonghuilv.aitravel.basic.intf.pojo.dto.WechatOpenidDTO;
import com.zhonghuilv.aitravel.basic.intf.pojo.vo.EncryptedData;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Create By zhengjing on 2018/4/28 14:49
 */
@FeignClient(value = BasicConstant.SERVICE_NAME)
@RequestMapping("/wechat")
public interface WechatClient {

    @ApiOperation("获取小程序openid sessionKey")
    @RequestMapping(value = "/openid", method = RequestMethod.POST)
    WechatOpenidDTO openid(@RequestParam("code") String code,
                           @RequestParam(value = "appid", required = false)
                                   String appid);

    @ApiOperation("微信小程序解密")
    @RequestMapping(value = "/decrypt", method = RequestMethod.POST)
    String decyct(@RequestBody EncryptedData endata);
}
