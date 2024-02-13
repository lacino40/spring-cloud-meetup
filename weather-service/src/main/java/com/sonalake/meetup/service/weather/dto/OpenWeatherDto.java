package com.sonalake.meetup.service.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

import static java.text.MessageFormat.format;
import static java.time.Instant.ofEpochSecond;
import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.of;
import static java.time.format.DateTimeFormatter.ofPattern;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherDto {
    private List<Weather> weather;
    private Weather displayWeather;
    private Main main;
    private Wind wind;
    private String name;
    private Long dt;
    private String displayTime;
    private String visibility;
    private String iconUrl;
    private boolean mock;
    private boolean error;

    public OpenWeatherDto withError() {
        this.error = true;
        return this;
    }

    /**
     * Retrieves the very first weather object to display from the list of weather objects.
     *
     * @return the weather object to display, or null if the list is empty
     */
    public Weather getDisplayWeather() {
        return  weather.stream()
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves the display time in the format "HH:mm".
     *
     * @return the display time
     */
    @JsonProperty
    public String getDisplayTime() {
        return ofInstant(ofEpochSecond(dt), of("UTC"))
                .format(ofPattern("HH:mm"));
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

    /**
     * Sets the icon URL for the OpenWeatherDto object.
     *
     * @param iconUrl the URL of the icon
     * @return the updated OpenWeatherDto object
     */
    public OpenWeatherDto withIconUrl(String iconUrl) {
        this.iconUrl = format(iconUrl, getDisplayWeather().getIcon());
        return this;
    }

    /**
     * Updates the mock flag and the name of the OpenWeatherDto object based on the provided arguments.
     *
     * @param mockFlag    a boolean indicating whether the object should be mocked
     * @param query       the query string used to update the name if the object is mocked
     * @return the updated OpenWeatherDto object
     */
    public OpenWeatherDto withMockFlag(boolean mockFlag, String query) {
        this.mock = mockFlag;
        this.name = mock ? withNameFromQueryIfMock(query) : name;
        return this;
    }

    private String withNameFromQueryIfMock(String query) {
        return query.split(",")[0];
    }
}
