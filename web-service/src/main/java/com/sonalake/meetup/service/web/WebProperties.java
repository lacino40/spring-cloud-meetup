package com.sonalake.meetup.service.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.util.Map;

@Data
@ConfigurationProperties("service.web")
public class WebProperties {
    private Map<String, String> serviceUrls;

    public URI getLocationUrl() {
        return URI
                .create(serviceUrls.get("location-service"));
    }

    public URI getWeatherUrl() {
        return URI
                .create(serviceUrls.get("weather-service"));
    }
}
