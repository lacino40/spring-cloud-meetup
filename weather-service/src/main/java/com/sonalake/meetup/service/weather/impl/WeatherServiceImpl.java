package com.sonalake.meetup.service.weather.impl;

import com.sonalake.meetup.service.weather.WeatherProperties;
import com.sonalake.meetup.service.weather.WeatherService;
import com.sonalake.meetup.service.weather.dto.WeatherDto;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate;
    private final WeatherProperties weatherProperties;

    @Override
    public WeatherDto getWeather(String query) {
        return new WeatherDto();
    }
}
