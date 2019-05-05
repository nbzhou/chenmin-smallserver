package com.zhonghuilv.aitravel.basic.intf.clients;

import com.zhonghuilv.aitravel.basic.intf.BasicConstant;
import com.zhonghuilv.aitravel.common.intf.BasicClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.Banner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhengjing  on 2018-06-06 10:41:54
 */
@FeignClient(value = BasicConstant.SERVICE_NAME)
@RequestMapping("/banner")
public interface BannerClient extends BasicClient<Banner> {

}

