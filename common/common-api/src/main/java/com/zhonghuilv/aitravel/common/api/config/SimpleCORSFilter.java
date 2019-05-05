package com.zhonghuilv.aitravel.common.api.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域过滤器
 *
 * @author zhengjing
 * @version 1.0
 * @date: 2017/10/31 15:41
 */
@Component
@Order(-988)
public class SimpleCORSFilter implements Filter {

    @Value("${cors.ignored:}")
    private String corsIgnored;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;


        if (StringUtils.isNotBlank(corsIgnored)) {//忽略的路径
            boolean cors = true;
            AntPathRequestMatcher matcher = null;
            for (String req : corsIgnored.split(",")) {
                matcher = new AntPathRequestMatcher(req);
                if (matcher.matches(request)) {
                    cors = false;
                    break;
                }
                if (cors) {
                    setCors(response);
                }
            }
        } else {
            setCors(response);
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    private void setCors(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET,PUT,PATCH, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
