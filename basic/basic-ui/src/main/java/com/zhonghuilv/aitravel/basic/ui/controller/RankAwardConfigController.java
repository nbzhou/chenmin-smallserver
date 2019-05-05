package com.zhonghuilv.aitravel.basic.ui.controller;

import com.zhonghuilv.aitravel.basic.intf.clients.RankAwardConfigClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.RankAwardConfig;
import com.zhonghuilv.aitravel.basic.ui.service.RunDataAwardRecordService;
import com.zhonghuilv.aitravel.common.ui.controller.BasicUIController;
import com.zhonghuilv.aitravel.travel.intf.pojo.Scenic;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Created by zhengjing  on 2018-05-19 16:54:23
 */
@RestController
@RequestMapping("/rank_award_config")
@Api(value = "RankAwardConfigController", description = "排名奖励配置", hidden = true)
public class RankAwardConfigController extends BasicUIController<RankAwardConfig> {


    private RankAwardConfigClient rankAwardConfigClient;

    @Autowired
    RunDataAwardRecordService runDataAwardRecordService;

    @Autowired
    public RankAwardConfigController(RankAwardConfigClient rankAwardConfigClient) {
        super(rankAwardConfigClient);
        this.rankAwardConfigClient = rankAwardConfigClient;
    }

    @GetMapping("/_grant_award")
    public void rundata(@RequestParam("scenicId") Long scenicId,
                        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        Scenic scenic = new Scenic();
        scenic.setId(scenicId);
        runDataAwardRecordService.grantAward(scenic, date);
    }

}

