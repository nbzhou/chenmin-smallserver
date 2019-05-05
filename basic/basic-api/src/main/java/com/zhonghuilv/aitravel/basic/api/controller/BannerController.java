package com.zhonghuilv.aitravel.basic.api.controller;

import com.zhonghuilv.aitravel.basic.intf.clients.BannerClient;
import com.zhonghuilv.aitravel.basic.intf.enums.EnumBannerCode;
import com.zhonghuilv.aitravel.basic.intf.pojo.Banner;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.common.constants.CommonConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhengjing  on 2018-04-29 17:12:50
 */
@RestController
@RequestMapping("/banner")
@Api(value = "BannerController", description = "轮播图")
public class BannerController {

    @Autowired
    private BannerClient bannerClient;


    @ApiOperation("获取积分商城轮播图")
    @RequestMapping(value = "/integral_mall", method = RequestMethod.GET)
    public ApiResult<List<Banner>> getIntegralBanners(@RequestHeader(CommonConst.DATA_AUTHORITY_HEADER_NAME)
                                                                  Long senicId) {

        Banner banner = new Banner();
        banner.setBannerCode(EnumBannerCode.INTEGRAL_MALL.name());
        banner.setScenicId(senicId);
        return ApiResult.success(bannerClient.loadList(banner));
    }
}

