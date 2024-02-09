package com.sonalake.meetup.service.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherDto {
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private String name;
    private String visibility;

    @Data
    public static class Weather {
        private Long id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    private static class Main {
        private String temp;
        private String feels_like;
        private String pressure;
        private String humidity;
    }

    @Data
    private static class Wind {
        private String speed;
    }

    public Weather getDisplayWeather() {
        return  weather.stream()
                .findFirst()
                .orElse(null);
    }
}
