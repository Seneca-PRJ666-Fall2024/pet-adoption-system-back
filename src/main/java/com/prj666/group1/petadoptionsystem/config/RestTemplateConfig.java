package com.prj666.group1.petadoptionsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate defaultRestTemplate() {
        return new RestTemplate();
    }
}
