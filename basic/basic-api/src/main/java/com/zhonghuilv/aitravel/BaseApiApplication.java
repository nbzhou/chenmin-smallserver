package com.zhonghuilv.aitravel;

import com.zhonghuilv.aitravel.common.api.config.CorsResourceServerConfigurerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

/**
 * Create By zhengjing on 2017/12/7 15:19
 */
@SpringCloudApplication
@EnableFeignClients
@EnableResourceServer
public class BaseApiApplication extends CorsResourceServerConfigurerAdapter{

    public static void main(String[] args) {
        SpringApplication.run(BaseApiApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced
    public OAuth2RestOperations restTemplate(UserInfoRestTemplateFactory factory) {

        return factory.getUserInfoRestTemplate();
    }

    @Bean
    public MethodValidationPostProcessor validationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }


}
