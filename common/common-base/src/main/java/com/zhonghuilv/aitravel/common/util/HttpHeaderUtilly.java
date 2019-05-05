package com.zhonghuilv.aitravel.common.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Create By zhengjing on 2018/5/8 15:37
 */
public class HttpHeaderUtilly {

    /**
     * 获取当前请求头信息
     *
     * @param headerName
     * @return
     */
    public static String getHeaderValue(String headerName) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest().getHeader(headerName);
        }
        return null;
    }

    /**
     * 获取当前请求头信息
     *
     * @param headerName
     * @return
     */
    public static String getHeaderValueOrDefault(String headerName, String defaultValue) {
        String result = getHeaderValue(headerName);
        return StringUtils.isBlank(result) ? defaultValue : result;
    }
}
