package com.sonalake.meetup.service.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.util.Map;

@Data
@ConfigurationProperties("service.web")
public class WebProperties {
    private Map<String, String> serviceUrls;

    public URI getLocationUrlFor(String path) {
        return URI
                .create(serviceUrls.get("location-service"))
                .resolve(path);
    }
}
