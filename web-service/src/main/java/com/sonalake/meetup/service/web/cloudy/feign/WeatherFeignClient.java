package com.sonalake.meetup.service.web.cloudy.feign;

import com.sonalake.meetup.service.web.dto.OpenWeatherDto;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "weather-service", fallback = WeatherFeignClientFallback.class)
public interface WeatherFeignClient {

    @LoadBalanced
    @GetMapping("/weather")
    OpenWeatherDto getWeather(@RequestParam String query);
}