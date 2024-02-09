package com.sonalake.meetup.service.weather;

import com.sonalake.meetup.service.weather.dto.OpenWeatherDto;

import java.io.IOException;

public interface WeatherService {
    OpenWeatherDto getWeather(String query) throws IOException;
}
