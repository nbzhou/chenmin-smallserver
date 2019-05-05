package com.zhonghuilv.aitravel.basic.intf.clients;

import com.zhonghuilv.aitravel.basic.intf.BasicConstant;
import com.zhonghuilv.aitravel.basic.intf.pojo.Config;
import com.zhonghuilv.aitravel.common.intf.BasicClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhengjing  on 2018-05-23 09:22:22
 */
@FeignClient(value = BasicConstant.SERVICE_NAME)
@RequestMapping("/config")
public interface ConfigClient extends BasicClient<Config> {

    @RequestMapping(value = "/_search_one", method = RequestMethod.GET)
    Config getConfigByCode(@RequestParam("scenicId") Long scenicId,
                     @RequestParam("code") String code);
}

