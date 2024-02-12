package com.sonalake.meetup.service.web.dto;

import lombok.Data;

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
    private boolean error;


    /**
     * Sets the error flag to true for the OpenWeatherDto object.
     *
     * @return The OpenWeatherDto object with the error flag set to true.
     */
    public OpenWeatherDto flagError() {
        this.error = true;
        return this;
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
