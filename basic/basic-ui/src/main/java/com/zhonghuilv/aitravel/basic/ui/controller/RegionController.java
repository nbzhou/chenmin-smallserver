package com.zhonghuilv.aitravel.basic.ui.controller;

import com.zhonghuilv.aitravel.basic.intf.clients.RegionClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.Region;
import com.zhonghuilv.aitravel.common.ui.controller.BasicUIController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengjing  on 2018-05-29 13:02:55
 */
@RestController
@RequestMapping("/region")
@Api(value = "RegionController", description = "行政区划")
public class RegionController extends BasicUIController<Region>{


	private RegionClient regionClient;

    @Autowired
    public RegionController(RegionClient regionClient){
        super(regionClient);
        this.regionClient = regionClient;
    }


}

