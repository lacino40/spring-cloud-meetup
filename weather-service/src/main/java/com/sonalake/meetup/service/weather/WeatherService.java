package com.sonalake.meetup.service.weather;

import com.sonalake.meetup.service.weather.dto.WeatherDto;

public interface WeatherService {
    WeatherDto getWeather(String query);
}
