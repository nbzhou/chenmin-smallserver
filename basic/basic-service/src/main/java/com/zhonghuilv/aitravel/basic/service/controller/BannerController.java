package com.zhonghuilv.aitravel.basic.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import com.zhonghuilv.aitravel.common.service.controller.BasicController;
import com.zhonghuilv.aitravel.basic.intf.pojo.Banner;
import com.zhonghuilv.aitravel.basic.intf.clients.BannerClient;
import com.zhonghuilv.aitravel.basic.service.mapper.BannerMapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zhengjing  on 2018-06-06 10:41:54
 */
@RestController
@RequestMapping("/banner")
@Api(value = "BannerController", description = "轮播图")
public class BannerController extends BasicController<Banner> implements BannerClient{

	private BannerMapper bannerMapper;

    @Autowired
    public BannerController(BannerMapper bannerMapper) {
        super(bannerMapper);
        this.bannerMapper =bannerMapper;
    }


}

