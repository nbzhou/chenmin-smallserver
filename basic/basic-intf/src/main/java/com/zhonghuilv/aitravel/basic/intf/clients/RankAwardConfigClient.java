package com.zhonghuilv.aitravel.basic.intf.clients;

import com.zhonghuilv.aitravel.basic.intf.BasicConstant;
import com.zhonghuilv.aitravel.basic.intf.pojo.RankAwardConfig;
import com.zhonghuilv.aitravel.common.intf.BasicClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenmin  on 2018-05-19 16:49:53
 */
@FeignClient(value = BasicConstant.SERVICE_NAME)
@RequestMapping("/rank_award_config")
public interface RankAwardConfigClient extends BasicClient<RankAwardConfig> {

}

