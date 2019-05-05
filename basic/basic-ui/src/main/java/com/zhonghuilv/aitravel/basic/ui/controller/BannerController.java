package com.zhonghuilv.aitravel.basic.ui.controller;

import com.zhonghuilv.aitravel.basic.intf.clients.BannerClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import com.zhonghuilv.aitravel.common.ui.controller.BasicUIController;

/**
 * Created by zhengjing  on 2018-06-06 10:41:54
 */
@RestController
@RequestMapping("/banner")
@Api(value = "BannerController", description = "轮播图")
public class BannerController extends BasicUIController<Banner>{


	private BannerClient bannerClient;

    @Autowired
    public BannerController(BannerClient bannerClient){
        super(bannerClient);
        this.bannerClient = bannerClient;
    }


}

