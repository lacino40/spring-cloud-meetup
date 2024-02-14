package com.sonalake.meetup.service.web.cloud.gateway;

import com.sonalake.meetup.service.web.WebProperties;
import com.sonalake.meetup.service.web.WebService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "service.web.config", havingValue = "gateway")
@EnableConfigurationProperties(WebProperties.class)
public class WebGatewayConfig {

    @Bean
    public WebService webService(GatewayFeignClient gatewayFeignClient) {
        return new WebServiceGatewayImpl(gatewayFeignClient);
    }

    @Bean
    public GatewayFeignClientFallbackFactory gatewayFeignClientFallbackFactory() {
        return new GatewayFeignClientFallbackFactory();
    }
}
