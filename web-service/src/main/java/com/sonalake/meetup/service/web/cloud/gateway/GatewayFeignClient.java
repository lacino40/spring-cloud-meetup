package com.sonalake.meetup.service.web.cloud.gateway;

import com.sonalake.meetup.service.web.dto.LocationDto;
import com.sonalake.meetup.service.web.dto.WeatherDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "gateway-service", fallbackFactory = GatewayFeignClientFallbackFactory.class)
public interface GatewayFeignClient {

    @GetMapping("/location")
    LocationDto[] getLocations();

    @GetMapping("/weather")
    WeatherDto getWeather(@RequestParam String query);
}