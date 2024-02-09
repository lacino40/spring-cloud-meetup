package com.sonalake.meetup.service.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherDto {
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private String name;
    private String visibility;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Weather {
        private Long id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Main {
        private String temp;
        private String feels_like;
        private String pressure;
        private String humidity;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Wind {
        private String speed;
    }
}
