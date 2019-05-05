package com.zhonghuilv.aitravel.common.api.config.feign;

import com.zhonghuilv.aitravel.authorization.common.util.UserUtil;
import com.zhonghuilv.aitravel.common.constants.ApplicationConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

/**
 * Create By zhengjing on 2018/5/7 14:35
 */
@Component
public class CustomRequestInterceptor implements HttpRequestInterceptor {
    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        Long userID = UserUtil.getUserId();
        if (userID != null) {
            httpRequest.setHeader(ApplicationConfig.FEIGN_HEADER_USER_ID, String.valueOf(userID));
        }

        //数据权限
        String headerValue = getHeaderValue(ApplicationConfig.FEIGN_HTTP_HEADER_DATA_AUTHORITIES);
        if (StringUtils.isNotBlank(headerValue)) {
            httpRequest.setHeader(ApplicationConfig.FEIGN_HTTP_HEADER_DATA_AUTHORITIES, headerValue);
        }
    }

    public String getHeaderValue(String headerName) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest().getHeader(headerName);
        }
        return null;
    }
}
