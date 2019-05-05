package com.chenmin;

import com.chenmin.config.CustomOAuth2HeaderProvider;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import de.codecentric.boot.admin.server.web.client.BasicAuthHttpHeaderProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestOperations;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableAdminServer
@EnableOAuth2Sso
public class SpringBootAdminApplication extends WebSecurityConfigurerAdapter {

    @Bean
    BasicAuthHttpHeaderProvider basicAuthHttpHeaderProvider() {
        return new CustomOAuth2HeaderProvider();
    }

    @Bean
    public OAuth2RestOperations restTemplate(UserInfoRestTemplateFactory factory) {

        return factory.getUserInfoRestTemplate();
    }

    public static void main(String[] args) {

        SpringApplication.run(SpringBootAdminApplication.class, args);
    }

//    @Profile("insecure")
//    @Configuration
//    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests().anyRequest().permitAll()//
//                    .and().csrf().disable();
//        }
//    }


    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**", "/*.js", "/*.css");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/applications")
                .permitAll()
                .antMatchers("/health", "/info").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

}