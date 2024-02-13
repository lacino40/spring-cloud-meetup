package com.sonalake.meetup.service.weather;

import com.sonalake.meetup.service.weather.dto.OpenWeatherDto;

public interface WeatherService {
    OpenWeatherDto requestOpenWeatherApi(String query) throws Exception;
}
