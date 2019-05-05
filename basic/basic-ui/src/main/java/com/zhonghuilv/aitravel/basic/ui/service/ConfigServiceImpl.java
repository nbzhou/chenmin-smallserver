package com.zhonghuilv.aitravel.basic.ui.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhonghuilv.aitravel.basic.intf.clients.ConfigClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.Config;
import com.zhonghuilv.aitravel.basic.intf.pojo.RunDataAwardConfig;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.common.excption.ParameterNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

/**
 * Create By zhengjing on 2018/5/23 09:40
 */
@Service
@Slf4j
public class ConfigServiceImpl implements ConfigService{
    @Autowired
    private ConfigClient configClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Config getConfig(Long scenicId, String code) {
        Config query = new Config();
        query.setScenicId(scenicId);
        query.setConfigCode(code);
        return configClient.loadOne(query);

    }

    @Override
    public RunDataAwardConfig getRunDataAwardConfig(Long scenicId) {
        Config config = getConfig(scenicId, RunDataAwardConfig.CONFIG_CODE);
        if (Objects.nonNull(config)) {
            try {
                return objectMapper.readValue(config.getConfigValue(), RunDataAwardConfig.class);
            } catch (IOException e) {
                log.error("步数奖励配置数据转换失败", e);
                throw new ParameterNotValidException("数据异常请联系管理员！");
            }
        }
        return null;

    }

    @Override
    public Config getIntegralExchangeFlow(Long scenicId) {


        return null;
    }
}
