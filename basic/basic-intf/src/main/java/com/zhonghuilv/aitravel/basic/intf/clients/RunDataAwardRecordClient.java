package com.zhonghuilv.aitravel.basic.intf.clients;

import com.zhonghuilv.aitravel.basic.intf.BasicConstant;
import com.zhonghuilv.aitravel.basic.intf.pojo.RunDataAwardRecord;
import com.zhonghuilv.aitravel.basic.intf.pojo.dto.RunDataAwardRecordQueryVO;
import com.zhonghuilv.aitravel.common.intf.BasicClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by zhengjing  on 2018-05-23 10:28:17
 */
@FeignClient(value = BasicConstant.SERVICE_NAME)
@RequestMapping("/run_data_award_record")
public interface RunDataAwardRecordClient extends BasicClient<RunDataAwardRecord> {

    @RequestMapping(value = "/_search_by_query",method = RequestMethod.POST)
    List<RunDataAwardRecord> loadByQuery(@RequestBody RunDataAwardRecordQueryVO queryVO);
}

