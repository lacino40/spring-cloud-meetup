package com.sonalake.meetup.service.web.cloud.feign;

import com.sonalake.meetup.service.web.WebProperties;
import com.sonalake.meetup.service.web.WebService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "service.web.config", havingValue = "feign")
@EnableConfigurationProperties(WebProperties.class)
public class WebFeignConfig {

    @Bean
    public WebService webService(LocationFeignClient locationFeignClient,
                                      WeatherFeignClient weatherFeignClient) {
        return new WebServiceFeignImpl(locationFeignClient, weatherFeignClient);
    }

    @Bean
    public WeatherFeignClientFallbackFactory weatherFeignClientFallbackFactory() {
        return new WeatherFeignClientFallbackFactory();
    }

    @Bean
    public LocationFeignClientFallback locationFeignClientFallback() {
        return new LocationFeignClientFallback();
    }
}
