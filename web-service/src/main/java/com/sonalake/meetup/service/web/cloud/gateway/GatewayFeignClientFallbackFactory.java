package com.sonalake.meetup.service.web.cloud.gateway;

import com.sonalake.meetup.service.web.dto.LocationDto;
import com.sonalake.meetup.service.web.dto.WeatherDto;
import org.springframework.cloud.openfeign.FallbackFactory;

public class GatewayFeignClientFallbackFactory implements FallbackFactory<GatewayFeignClient> {
    @Override
    public GatewayFeignClient create(Throwable cause) {
        return new GatewayFeignClient() {
            @Override
            public LocationDto[] getLocations() {
                return null;
            }

            @Override
            public WeatherDto getWeather(String query) {
                return new WeatherDto()
                        .withThrowable("open-weather-api", cause);
            }
        };
    }
}
