package com.sonalake.meetup.service.web.cloud.feign;

import com.sonalake.meetup.service.web.dto.OpenWeatherDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;

public class WeatherFeignClientFallbackFactory implements FallbackFactory<WeatherFeignClient> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherFeignClientFallbackFactory.class);
    @Override
    public WeatherFeignClient create(Throwable cause) {
        LOGGER.error("open-weather-api is not available");

        return query ->
                new OpenWeatherDto()
                        .withThrowable(
                                "open-weather-api",
                                cause);
    }
}
