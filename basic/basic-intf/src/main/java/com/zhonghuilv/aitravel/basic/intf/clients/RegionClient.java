package com.zhonghuilv.aitravel.basic.intf.clients;

import com.zhonghuilv.aitravel.basic.intf.BasicConstant;
import com.zhonghuilv.aitravel.common.intf.BasicClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.Region;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhengjing  on 2018-05-29 13:02:55
 */
@FeignClient(value = BasicConstant.SERVICE_NAME)
@RequestMapping("/region")
public interface RegionClient extends BasicClient<Region> {

}

