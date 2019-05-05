package com.zhonghuilv.aitravel.basic.ui.service;

import com.zhonghuilv.aitravel.basic.intf.pojo.Config;
import com.zhonghuilv.aitravel.basic.intf.pojo.RunDataAwardConfig;

/**
 * Create By zhengjing on 2018/5/23 09:40
 */
public interface ConfigService {

    Config getConfig(Long scenicId, String code);

    RunDataAwardConfig getRunDataAwardConfig(Long scenicId);

    Config getIntegralExchangeFlow(Long scenicId);
}
