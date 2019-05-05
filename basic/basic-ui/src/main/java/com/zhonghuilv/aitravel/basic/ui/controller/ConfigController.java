package com.zhonghuilv.aitravel.basic.ui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhonghuilv.aitravel.basic.intf.clients.ConfigClient;
import com.zhonghuilv.aitravel.basic.intf.enums.EnumConfigCode;
import com.zhonghuilv.aitravel.basic.intf.pojo.Config;
import com.zhonghuilv.aitravel.basic.intf.pojo.RunDataAwardConfig;
import com.zhonghuilv.aitravel.basic.ui.service.ConfigService;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.common.excption.ParameterNotValidException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import com.zhonghuilv.aitravel.common.ui.controller.BasicUIController;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by zhengjing  on 2018-05-23 09:22:22
 */
@RestController
@RequestMapping("/config")
@Slf4j
@Api(value = "ConfigController", description = "配置信息")
public class ConfigController {

    private ConfigClient configClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ConfigService configService;

    @Autowired
    public ConfigController(ConfigClient configClient) {
        this.configClient = configClient;
    }

    @ApiOperation("获取步数奖励配置")
    @GetMapping("/run_data_award")
    public ApiResult<RunDataAwardConfig> getRunDataAward(@RequestParam("scenicId") Long scenicId) {

        return ApiResult.success(configService.getRunDataAwardConfig(scenicId));
    }

    @ApiOperation("保存步数奖励配置")
    @PostMapping("/run_data_award")
    public ApiResult<Boolean> saveRunDataAward(@RequestParam("scenicId") Long scenicId,
                                               @RequestBody RunDataAwardConfig runDataAwardConfig) throws
            JsonProcessingException {

        Config config = configService.getConfig(scenicId, RunDataAwardConfig.CONFIG_CODE);
        String s = objectMapper.writeValueAsString(runDataAwardConfig);
        if (Objects.nonNull(config)) {
            config.setConfigValue(s);
            configClient.updateSelective(config);
            return ApiResult.success(true);
        }
        Config save = new Config();
        save.setScenicId(scenicId);
        save.setConfigValue(s);
        save.setConfigCode(RunDataAwardConfig.CONFIG_CODE);
        configClient.save(save);
        return ApiResult.success(true);
    }

    @ApiOperation("获取积分兑换流程")
    @GetMapping("/integral_exchange_flow")
    public ApiResult<String> getIntegralExchangeFlow(@RequestParam("scenicId") Long scenicId) {
        String s = Optional.ofNullable(configService.getConfig(scenicId, EnumConfigCode.INTEGRAL_EXCHANGE_FLOW.name()))
                .map(Config::getConfigValue).orElse(null);
        return ApiResult.success(s);
    }

    @ApiOperation("保存积分兑换流程配置")
    @PostMapping("/integral_exchange_flow")
    public ApiResult<Boolean> saveIntegralExchangeFlow(@RequestParam("scenicId") Long scenicId,
                                                       @RequestBody String integralExchangeFlow) {

        Config config = configService.getConfig(scenicId, EnumConfigCode.INTEGRAL_EXCHANGE_FLOW.name());
        if (Objects.nonNull(config)) {
            config.setConfigValue(integralExchangeFlow);
            configClient.updateSelective(config);
            return ApiResult.success(true);
        }
        Config save = new Config();
        save.setScenicId(scenicId);
        save.setConfigValue(integralExchangeFlow);
        save.setConfigCode(EnumConfigCode.INTEGRAL_EXCHANGE_FLOW.name());
        configClient.save(save);
        return ApiResult.success(true);
    }
}

