package com.sonalake.meetup.service.web.cloud.feign;

import com.sonalake.meetup.service.web.ForecastService;
import com.sonalake.meetup.service.web.WebProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "service.web.config", havingValue = "feign")
@EnableConfigurationProperties(WebProperties.class)
public class WebFeignConfig {

    @Bean
    public ForecastService forecastService(LocationFeignClient locationFeignClient,
                                           WeatherFeignClient weatherFeignClient) {
        return new ForecastServiceFeignImpl(locationFeignClient, weatherFeignClient);
    }

    @Bean
    public WeatherFeignClientFallback weatherFeignClientFallback() {
        return new WeatherFeignClientFallback();
    }
}
