package com.chenmin.config;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.web.client.BasicAuthHttpHeaderProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * 自定义oauth2 授权
 * 由于 admin 内部源码写死一个授权bean 覆盖
 * Create By zhengjing on 2018/5/15 11:44
 */
public class CustomOAuth2HeaderProvider extends BasicAuthHttpHeaderProvider {

    private final String SERVICE_NAME = "SERVICE";

    @Override
    public HttpHeaders getHeaders(Instance instance) {

        HttpHeaders headers = new HttpHeaders();
        if (instance.getRegistration().getName().endsWith(SERVICE_NAME)) {
            return headers;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication != null) {
            Object details = authentication.getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) details;
                headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + oAuth2AuthenticationDetails.getTokenValue());
            }
        }
        return headers;
    }
}
