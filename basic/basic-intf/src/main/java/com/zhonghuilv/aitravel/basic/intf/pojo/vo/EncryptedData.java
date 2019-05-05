package com.zhonghuilv.aitravel.basic.intf.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Create By zhengjing on 2018/4/3 10:41
 */
@ApiModel("微信加密数据")
@Data
public class EncryptedData {

    @ApiModelProperty("小程序appid")
    private String appid;

    private String encryptedData;

    private String iv;

    private String code;
}
