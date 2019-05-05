package com.zhonghuilv.aitravel.common.api.config;

import com.zhonghuilv.aitravel.common.constants.CommonConst;
import com.zhonghuilv.aitravel.common.util.HttpHeaderUtilly;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Create By zhengjing on 2018/5/8 15:44
 */
@Component
public class AuthorityRequestInterceptor implements HttpRequestInterceptor {

    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        String authority = HttpHeaderUtilly.getHeaderValueOrDefault(CommonConst.DATA_AUTHORITY_HEADER_NAME,
                CommonConst.DEFAULT_DATA_AUTHORITY);
        httpRequest.setHeader(CommonConst.DATA_AUTHORITY_HEADER_NAME, authority);
    }
}
