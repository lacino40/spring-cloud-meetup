package com.sonalake.meetup.service.location;

import com.sonalake.meetup.service.location.dto.LocationDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class LocationController extends LocationUtility {
    private final LocationProperties locationProperties;

    public LocationController(LocationProperties locationProperties) {
        this.locationProperties = locationProperties;
    }

    @GetMapping("/location")
    public Set<LocationDto> getLocations() {

        return sortLocationById(
                locationProperties.getLocations()
        );
    }
}
