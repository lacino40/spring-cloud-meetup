package com.sonalake.meetup.service.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static java.util.Objects.nonNull;

@Data
public class OpenWeatherDto {
    private Weather displayWeather;
    private Main main;
    private Wind wind;
    private String name;
    private String displayTime;
    private String visibility;
    private String iconUrl;
    private boolean mock;
    private String errorMessage;

    public OpenWeatherDto withError(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    @JsonProperty
    public boolean hasError() {
        return nonNull(errorMessage);
    }

    @Data
    private static class Weather {
        private String main;
        private String description;
        private String icon;
    }

    @Data
    public static class Main {
        private Double temp;
        private Double feels_like;
        private String pressure;
        private String humidity;
    }

    @Data
    public static class Wind {
        private Double speed;
    }
}
