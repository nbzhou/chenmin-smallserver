package com.zhonghuilv.aitravel.common.api.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;

/**
 * 跨域支持
 * Created by 郑靖 on 2017/9/27.
 */
public class CorsResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {



    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        super.configure(resources);
//        OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
//        oAuth2AuthenticationEntryPoint.setExceptionRenderer(new CustomExceptionTranslator());
//        resources.accessDeniedHandler(new CustomAccessDeniedHandler());
//        resources.authenticationEntryPoint(oAuth2AuthenticationEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        configure(http, FilterSecurityInterceptor.class);

    }

    protected void configure(HttpSecurity http, Class<? extends Filter> beforeFilter) throws Exception {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        CorsFilter corsFilter = new CorsFilter(source);

//        http.securityContext().
        http
                //兼容以前的
                .authorizeRequests()
                .requestMatchers(EndpointRequest.to("health", "info"))
                .permitAll()
                .antMatchers(HttpMethod.GET,"/actuator")
                .permitAll()
                .requestMatchers(EndpointRequest.toAnyEndpoint())
                .hasRole("ACTUATOR").and()
                //兼容 security.ignored
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(corsFilter, beforeFilter);
    }

}
