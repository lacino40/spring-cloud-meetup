package com.sonalake.meetup.service.weather;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Data
@ConfigurationProperties("service.weather.open-weather")
public class WeatherProperties {
    private String url;
    private String version;
    private String unit;
    private String apiKey;

    public URI getOpenWeatherUrl(String query) {
        return UriComponentsBuilder
                .fromHttpUrl(url)
                .pathSegment(version, "weather")
                .queryParam("q", query)
                .queryParam("units", unit)
                .queryParam("APPID", apiKey)
                .build()
                .toUri();
    }
}
