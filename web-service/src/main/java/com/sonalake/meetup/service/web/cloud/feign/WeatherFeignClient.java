package com.sonalake.meetup.service.web.cloud.feign;

import com.sonalake.meetup.service.web.dto.WeatherDto;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "weather-service", fallbackFactory = WeatherFeignClientFallbackFactory.class)
public interface WeatherFeignClient {

    @LoadBalanced
    @GetMapping("/weather")
    WeatherDto getWeather(@RequestParam String query);
}