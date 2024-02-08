package com.sonalake.meetup.service.weather.dto;

import lombok.Data;

@Data
public class WeatherDto {
    private Long id;
    private String name;
    private String main;
    private String description;
    private String icon;
    private String temp;
    private String feelsLike;
    private String pressure;
    private String humidity;
    private String visibility;
    private String windSpeed;
}
