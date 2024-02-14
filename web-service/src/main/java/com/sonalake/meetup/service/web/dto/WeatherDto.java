package com.sonalake.meetup.service.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang.exception.ExceptionUtils.getStackTrace;

@Data
public class WeatherDto {
    private Weather displayWeather;
    private Main main;
    private Wind wind;
    @JsonIgnore
    private Error error;
    private String name;
    private String displayTime;
    private String visibility;
    private String iconUrl;
    private boolean mock;

    /**
     * Sets the error flag, error service name, and error stack trace when error occurred
     * while calling remote service
     *
     * @param errorServiceName the name of the error service
     * @param cause the throwable object to get the stack trace from
     * @return the OpenWeatherDto object
     */
    public WeatherDto withThrowable(String errorServiceName, Throwable cause) {
        setError(
            Error.from(errorServiceName, getStackTrace(cause))
        );

        return this;
    }

    public boolean isError() {
        return nonNull(error) && error.isError();
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

    @Data
    @AllArgsConstructor(staticName = "from")
    public static class Error {
        private final boolean error = true;
        private final String errorServiceName;
        private final String errorStackTrace;
    }
}
