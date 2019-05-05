package com.zhonghuilv.aitravel.basic.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import com.zhonghuilv.aitravel.common.service.controller.BasicController;
import com.zhonghuilv.aitravel.basic.intf.pojo.RankAwardConfig;
import com.zhonghuilv.aitravel.basic.intf.clients.RankAwardConfigClient;
import com.zhonghuilv.aitravel.basic.service.mapper.RankAwardConfigMapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by chenmin  on 2018-05-19 16:54:23
 */
@RestController
@RequestMapping("/rank_award_config")
@Api(value = "RankAwardConfigController", description = "排名奖励配置")
public class RankAwardConfigController extends BasicController<RankAwardConfig> implements RankAwardConfigClient{

	private RankAwardConfigMapper rankAwardConfigMapper;

    @Autowired
    public RankAwardConfigController(RankAwardConfigMapper rankAwardConfigMapper) {
        super(rankAwardConfigMapper);
        this.rankAwardConfigMapper =rankAwardConfigMapper;
    }


}

