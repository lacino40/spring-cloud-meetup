package com.sonalake.meetup.service.location;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@Getter
@Setter
@ConfigurationProperties("service.location")
public class LocationProperties {
    private Set<CityDto> cities;

    @Getter
    @Setter
    public static class CityDto {
        private Long id;
        private String name;
        private String query;
    }
}
