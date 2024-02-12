package com.sonalake.meetup.service.web.basic;

import com.sonalake.meetup.service.web.ForecastService;
import com.sonalake.meetup.service.web.WebProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConditionalOnProperty(name = "service.web.config", havingValue = "basic",  matchIfMissing = true)
@EnableConfigurationProperties(WebProperties.class)
public class WebBasicConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ForecastService forecastService(RestTemplate restTemplate, WebProperties webProperties) {
        return new ForecastServiceBasicImpl(restTemplate, webProperties);
    }
}
