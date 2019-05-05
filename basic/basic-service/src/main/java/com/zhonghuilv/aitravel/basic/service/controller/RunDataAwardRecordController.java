package com.zhonghuilv.aitravel.basic.service.controller;

import com.zhonghuilv.aitravel.basic.intf.pojo.dto.RunDataAwardRecordQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import com.zhonghuilv.aitravel.common.service.controller.BasicController;
import com.zhonghuilv.aitravel.basic.intf.pojo.RunDataAwardRecord;
import com.zhonghuilv.aitravel.basic.intf.clients.RunDataAwardRecordClient;
import com.zhonghuilv.aitravel.basic.service.mapper.RunDataAwardRecordMapper;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zhengjing  on 2018-05-23 10:28:17
 */
@RestController
@RequestMapping("/run_data_award_record")
@Api(value = "RunDataAwardRecordController", description = "步数奖励记录")
public class RunDataAwardRecordController extends BasicController<RunDataAwardRecord> implements
        RunDataAwardRecordClient {

    private RunDataAwardRecordMapper runDataAwardRecordMapper;

    @Autowired
    public RunDataAwardRecordController(RunDataAwardRecordMapper runDataAwardRecordMapper) {
        super(runDataAwardRecordMapper);
        this.runDataAwardRecordMapper = runDataAwardRecordMapper;
    }


    @Override
    @RequestMapping(value = "/_search_by_query", method = RequestMethod.POST)
    public List<RunDataAwardRecord> loadByQuery(@RequestBody RunDataAwardRecordQueryVO queryVO) {
        Example example = new Example(RunDataAwardRecord.class);

        example.createCriteria()
                .andEqualTo("scenicId", queryVO.getScenicId())
                .andBetween("awardDate", queryVO.getStartDate(), queryVO.getEndDate())
        ;
        return runDataAwardRecordMapper.selectByExample(example);
    }
}

