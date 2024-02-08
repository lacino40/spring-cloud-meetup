package com.sonalake.meetup.service.weather;

import com.sonalake.meetup.service.weather.dto.OpenWeatherDto;

public interface WeatherService {
    OpenWeatherDto getWeather(String query);
}
