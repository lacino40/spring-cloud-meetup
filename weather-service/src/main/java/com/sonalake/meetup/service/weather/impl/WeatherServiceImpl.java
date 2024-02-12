package com.sonalake.meetup.service.weather.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sonalake.meetup.service.weather.WeatherProperties;
import com.sonalake.meetup.service.weather.WeatherService;
import com.sonalake.meetup.service.weather.dto.OpenWeatherDto;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;

@AllArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate;
    private final WeatherProperties weatherProperties;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OpenWeatherDto requestOpenWeatherApi(String query) throws IOException {
        URI openWeatherURI = weatherProperties.getOpenWeatherUrl(query);
        String iconUrl =  weatherProperties.getIconUrl();
        boolean isMocked = weatherProperties.isMocked();

        OpenWeatherDto openWeatherDto =  isMocked ?
                objectMapper.readValue(Paths.get(openWeatherURI).toFile(), OpenWeatherDto.class) :
                restTemplate.getForObject(openWeatherURI, OpenWeatherDto.class);

        return requireNonNull(openWeatherDto)
                .withIconUrl(iconUrl)
                .withMockFlag(isMocked, query);
    }
}
