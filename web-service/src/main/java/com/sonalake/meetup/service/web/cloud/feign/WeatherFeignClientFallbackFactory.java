package com.sonalake.meetup.service.web.cloud.feign;

import com.sonalake.meetup.service.web.dto.WeatherDto;
import org.springframework.cloud.openfeign.FallbackFactory;

public class WeatherFeignClientFallbackFactory implements FallbackFactory<WeatherFeignClient> {
    @Override
    public WeatherFeignClient create(Throwable cause) {
        return query ->
                new WeatherDto()
                        .withThrowable("open-weather-api", cause);
    }
}
