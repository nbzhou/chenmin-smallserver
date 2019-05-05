package com.zhonghuilv.aitravel.common.constants;

/**
 * Create By zhengjing on 2018/2/1 13:38
 */
public interface CommonConst {

    /**
     * 数据权限 请求头(api 代表那个景区)
     */
    String DATA_AUTHORITY_HEADER_NAME = "x-data-authorities";

    /**
     * 默认 石燕湖
     */
    String DEFAULT_DATA_AUTHORITY = "1";
    Integer INT_ZERO = Integer.valueOf(0);

    Long LONG_ZERO = Long.valueOf(0L);

    String EMPTY_STRING = "";

    /**
     * 通用路径 分页查询
     */
    String PATH_PAGE_SEARCH = "_page_search";

    /**
     * 通用路径 列表查询
     */
    String PATH_SEARCH = "_search";
}

