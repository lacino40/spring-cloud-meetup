package com.sonalake.meetup.service.weather;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("service.weather.open-weather-map")
public class WeatherProperties {
    private String url;
    private String version;
    private String unit;
    private String apiKey;
}
