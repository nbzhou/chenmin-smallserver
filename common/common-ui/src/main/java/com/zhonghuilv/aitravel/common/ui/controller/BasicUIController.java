package com.zhonghuilv.aitravel.common.ui.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.common.intf.BasicClient;
import com.zhonghuilv.aitravel.common.pojo.dto.PageQuery;
import com.zhonghuilv.aitravel.common.pojo.dto.QueryExample;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Create By zhengjing on 2018/5/3 14:51
 */
public class BasicUIController<T> {

    private BasicClient<T> client;

    public BasicUIController(BasicClient<T> client) {
        this.client = client;
    }

    @ApiOperation("新增")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ApiResult<T> save(@Validated @RequestBody T model) {
        return ApiResult.success(client.save(model));
    }

    @ApiOperation("修改指定字段")
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public ApiResult<T> updateSelective(@RequestBody T model) {
        return ApiResult.success(client.updateSelective(model));
    }

    @ApiOperation("物理删除")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ApiResult<Boolean> delete(@PathVariable("id") Long id) {
        return ApiResult.success(client.delete(id));
    }

    //查询
    @ApiOperation("主键查询")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResult<T> loadById(@PathVariable("id") Long id) {
        return ApiResult.success(client.loadById(id));
    }

    @ApiOperation("单行查询")
    @RequestMapping(value = "/_search_one", method = RequestMethod.POST)
    public ApiResult<T> loadOne(@RequestBody T model) {
        return ApiResult.success(client.loadOne(model));
    }

    @ApiOperation("列表查询")
    @RequestMapping(value = "/_search", method = RequestMethod.POST)
    public ApiResult<List<T>> loadList(@RequestBody T model) {
        return ApiResult.success(client.loadList(model));
    }

    @ApiOperation("分页查询")
    @RequestMapping(value = "/_search_page", method = RequestMethod.POST)
    public ApiResult<PageInfo<T>> loadPage(@RequestBody PageQuery<T> query) {
        return ApiResult.success(client.loadPage(query));
    }

    @ApiOperation("自定义查询")
    @RequestMapping(value = "/_search_by_example", method = RequestMethod.POST)
    public ApiResult<Page<T>> searchByExample(@RequestBody QueryExample<T> pageQuery) {
        return ApiResult.success(client.searchByExample(pageQuery));
    }
}
