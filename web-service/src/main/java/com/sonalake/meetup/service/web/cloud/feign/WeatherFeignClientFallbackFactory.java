package com.sonalake.meetup.service.web.cloud.feign;

import com.sonalake.meetup.service.web.dto.OpenWeatherDto;
import org.springframework.cloud.openfeign.FallbackFactory;

public class WeatherFeignClientFallbackFactory implements FallbackFactory<WeatherFeignClient> {
    @Override
    public WeatherFeignClient create(Throwable cause) {
        return query ->
                new OpenWeatherDto()
                        .withThrowable("open-weather-api", cause);
    }
}
