package com.sonalake.meetup.service.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

import static java.text.MessageFormat.format;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherDto {
    private List<Weather> weather;
    private Weather displayWeather;
    private Main main;
    private Wind wind;
    private String name;
    private String visibility;
    private String iconUrl;
    private boolean mock;

    public Weather getDisplayWeather() {
        return  weather.stream()
                .findFirst()
                .orElse(null);
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {
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

    public OpenWeatherDto withIconUrl(String iconUrl) {
        this.iconUrl = format(iconUrl, getDisplayWeather().getIcon());
        return this;
    }

    public OpenWeatherDto withMockFlag(boolean mockFlag) {
        this.mock = mockFlag;
        return this;
    }
}
