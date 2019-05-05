package com.zhonghuilv.aitravel.common.constants;

/**
 * Create By zhengjing on 2018/1/15 14:22
 */
public interface ApplicationConfig {

    /**
     * 静态资源的域名
     */
//    String STATIC_DOMAIN = "http://cdn.shushan.com";

    /**
     * feifn 调用时传入userID的headName
     */
    String FEIGN_HEADER_USER_ID = "f-user-id";

    String FEIGN_HTTP_HEADER_DATA_AUTHORITIES = "x-data-authorities";

    Integer INT_ZERO = Integer.valueOf(0);

    Long LONG_ZERO = Long.valueOf(0L);

}
