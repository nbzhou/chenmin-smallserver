package com.chenmin.apigateway;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * creat time 2019-04-13
 * jdk version 1.8
 * 加密模式入口
 */
@EnableZuulProxy
@SpringCloudApplication
public class APIGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(APIGatewayApplication.class,args);
    }
}
