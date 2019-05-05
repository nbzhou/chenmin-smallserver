package com.zhonghuilv.aitravel.basic.ui.controller;

import com.github.pagehelper.Page;
import com.zhonghuilv.aitravel.basic.intf.clients.RunDataAwardRecordClient;
import com.zhonghuilv.aitravel.basic.intf.pojo.RunDataAwardRecord;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.common.pojo.dto.QueryExample;
import com.zhonghuilv.aitravel.common.ui.controller.BasicUIController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengjing  on 2018-05-23 10:28:17
 */
@RestController
@RequestMapping("/run_data_award_record")
@Api(value = "RunDataAwardRecordController", description = "步数奖励记录")
public class RunDataAwardRecordController /*extends BasicUIController<RunDataAwardRecord>*/{

	private RunDataAwardRecordClient runDataAwardRecordClient;

    @Autowired
    public RunDataAwardRecordController(RunDataAwardRecordClient runDataAwardRecordClient){
//        super(runDataAwardRecordClient);
        this.runDataAwardRecordClient = runDataAwardRecordClient;
    }

    @ApiOperation("自定义查询")
    @RequestMapping(value = "/_search_by_example", method = RequestMethod.POST)
    public ApiResult<Page<RunDataAwardRecord>> searchByExample(@RequestBody QueryExample<RunDataAwardRecord> pageQuery) {

        return ApiResult.success(runDataAwardRecordClient.searchByExample(pageQuery));
    }
}

