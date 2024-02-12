package com.sonalake.meetup.service.web.cloudy;

import com.sonalake.meetup.service.web.ForecastService;
import com.sonalake.meetup.service.web.WebProperties;
import com.sonalake.meetup.service.web.cloudy.feign.LocationFeignClient;
import com.sonalake.meetup.service.web.cloudy.feign.WeatherFeignClient;
import com.sonalake.meetup.service.web.cloudy.feign.WeatherFeignClientFallback;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "service.web.cloudy", havingValue = "true")
@EnableConfigurationProperties(WebProperties.class)
public class WebCloudyConfig {

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
