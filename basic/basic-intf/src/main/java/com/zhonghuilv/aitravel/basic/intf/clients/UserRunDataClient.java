package com.zhonghuilv.aitravel.basic.intf.clients;

import com.github.pagehelper.PageInfo;
import com.zhonghuilv.aitravel.basic.intf.BasicConstant;
import com.zhonghuilv.aitravel.basic.intf.pojo.UserRunData;
import com.zhonghuilv.aitravel.common.pojo.dto.PageQuery;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by zhengjing  on 2018-04-03 16:14:22
 */
@FeignClient(value = BasicConstant.SERVICE_NAME)
@RequestMapping("/user_run_data")
public interface UserRunDataClient {

    /**
     * 新增
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    UserRunData save(@RequestBody UserRunData model);

    /**
     * 全量修改
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    UserRunData update(@RequestBody UserRunData model);

    /**
     * 局部修改
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    UserRunData updateSelective(@RequestBody UserRunData model);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    boolean delete(@PathVariable("id") Long id);

    //查询

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    UserRunData loadById(@PathVariable("id") Long id);

    /**
     * 单行查询
     *
     * @return
     */
    @RequestMapping(value = "/_search_one", method = RequestMethod.POST)
    UserRunData loadOne(@RequestBody UserRunData model);

    @RequestMapping(value = "/_search", method = RequestMethod.POST)
    List<UserRunData> loadList(@RequestBody UserRunData model);

    @ApiOperation("分页查询")
    @RequestMapping(value = "/_search_page", method = RequestMethod.POST)
    PageInfo<UserRunData> loadPage(@RequestBody PageQuery<UserRunData> query);

}

