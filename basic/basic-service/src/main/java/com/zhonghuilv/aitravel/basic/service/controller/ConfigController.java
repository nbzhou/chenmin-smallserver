package com.zhonghuilv.aitravel.basic.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import com.zhonghuilv.aitravel.common.service.controller.BasicController;
import com.zhonghuilv.aitravel.basic.intf.pojo.Config;
import com.zhonghuilv.aitravel.basic.intf.clients.ConfigClient;
import com.zhonghuilv.aitravel.basic.service.mapper.ConfigMapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zhengjing  on 2018-05-23 09:22:22
 */
@RestController
@RequestMapping("/config")
@Api(value = "ConfigController", description = "配置信息")
public class ConfigController extends BasicController<Config> implements ConfigClient {

    private ConfigMapper configMapper;

    @Autowired
    public ConfigController(ConfigMapper configMapper) {
        super(configMapper);
        this.configMapper = configMapper;
    }


    @Override
    @RequestMapping(value = "/_search_one", method = RequestMethod.GET)
    public Config getConfigByCode(@RequestParam("scenicId") Long scenicId,
                                  @RequestParam("code") String code) {

        Config config = new Config();
        config.setScenicId(scenicId);
        config.setConfigCode(code);
        return configMapper.selectOne(config);
    }
}

