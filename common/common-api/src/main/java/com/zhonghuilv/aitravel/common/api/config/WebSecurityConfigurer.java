package com.zhonghuilv.aitravel.common.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create By zhengjing on 2018/5/12 11:08
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Value("${security.ignored:}")
    String[] antPatterns;

    String[] defaultIgnoring = new String[]{"/error", "/css/**", "/js/**", "/images/**", "/webjars/**", "/**/favicon" +
            ".ico", "/assets/**"};

    /**
     * Override this method to configure {@link WebSecurity}. For example, if you wish to
     * ignore certain requests.
     *
     * @param web
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        List<String> patterns= new ArrayList<>();
        patterns.addAll(Arrays.asList(antPatterns));
        patterns.addAll(Arrays.asList(defaultIgnoring));

        String[] end = patterns.stream().distinct().toArray(String[]::new);
        web.ignoring().antMatchers(end);
    }

}
