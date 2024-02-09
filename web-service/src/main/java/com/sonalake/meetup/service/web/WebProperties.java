package com.sonalake.meetup.service.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Data
@ConfigurationProperties("service.web")
public class WebProperties {
    private Map<String, String> serviceUrls;

    public URI getLocationURI() {
        return URI
                .create(serviceUrls.get("location-service"));
    }

    public URI getWeatherURI(String query) {
        return UriComponentsBuilder
                .fromHttpUrl(serviceUrls.get("weather-service"))
                .queryParam("query", query)
                .build()
                .toUri();
    }
}
