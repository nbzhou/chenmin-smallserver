package com.zhonghuilv.aitravel.common.ui.config;

import com.zhonghuilv.aitravel.authorization.intf.CustomAccessDecisionManager;
import com.zhonghuilv.aitravel.authorization.intf.CustomFilterSecurityInterceptor;
import com.zhonghuilv.aitravel.authorization.intf.CustomInvocationSecurityMetadataSource;
import com.zhonghuilv.aitravel.common.api.config.CorsResourceServerConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * 跨域 和权限 适配器
 * Create By zhengjing on 2018/1/6 11:09
 */
public class CorsAccessConfigurerAdapter extends CorsResourceServerConfigurerAdapter {

    @Lazy
    @Autowired
    private CustomAccessDecisionManager accessDecisionManager;

    @Lazy
    @Autowired
    private CustomInvocationSecurityMetadataSource securityMetadataSource;


    @Override
    public void configure(HttpSecurity http) throws Exception {

        CustomFilterSecurityInterceptor myFilterSecurityInterceptor = new CustomFilterSecurityInterceptor();
        myFilterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource);
        myFilterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);

        //拒绝 匹配不到的路径
//        myFilterSecurityInterceptor.setRejectPublicInvocations(true);

        http.addFilterBefore(myFilterSecurityInterceptor,
                FilterSecurityInterceptor.class);

        super.configure(http, CustomFilterSecurityInterceptor.class);

    }

}
