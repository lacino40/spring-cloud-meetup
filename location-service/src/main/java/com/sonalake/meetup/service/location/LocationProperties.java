package com.sonalake.meetup.service.location;

import com.sonalake.meetup.service.location.dto.CityDto;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@Data
@ConfigurationProperties("service.location")
public class LocationProperties {
    private Set<CityDto> cities;
}
