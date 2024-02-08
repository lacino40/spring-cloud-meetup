package com.sonalake.meetup.service.weather.impl;

import com.sonalake.meetup.service.weather.WeatherProperties;
import com.sonalake.meetup.service.weather.WeatherService;
import com.sonalake.meetup.service.weather.dto.OpenWeatherDto;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@AllArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate;
    private final WeatherProperties weatherProperties;

    @Override
    public OpenWeatherDto getWeather(String query) {
        URI openWeatherUrl = weatherProperties.getOpenWeatherUrl(query);

        return restTemplate.getForObject(openWeatherUrl, OpenWeatherDto.class);
    }
}
