package com.sonalake.meetup.service.web.cloud.feign;

import com.sonalake.meetup.service.web.dto.OpenWeatherDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

public class WeatherFeignClientFallback implements WeatherFeignClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherFeignClientFallback.class);

    @Override
    public OpenWeatherDto getWeather(String query) {
        LOGGER.error(format("weather-service not available for query %s", query));

        return new OpenWeatherDto().flagError();
    }
}
