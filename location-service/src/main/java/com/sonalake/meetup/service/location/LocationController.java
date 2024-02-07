package com.sonalake.meetup.service.location;

import com.sonalake.meetup.service.location.dto.CityDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/location")
public class LocationController {
    private final LocationProperties locationProperties;

    public LocationController(LocationProperties locationProperties) {
        this.locationProperties = locationProperties;
    }

    @RequestMapping("/cities")
    public Set<CityDto> getCities() {
        return locationProperties.getCities();
    }
}
