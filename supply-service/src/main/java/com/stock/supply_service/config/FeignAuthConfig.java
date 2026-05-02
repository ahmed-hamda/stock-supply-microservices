package com.stock.supply_service.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class FeignAuthConfig {

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return template -> {
            String auth = "admin:admin123";
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            template.header("Authorization", "Basic " + encodedAuth);
        };
    }
}