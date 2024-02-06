package com.sonalake.meetup.service.location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/location")
public class LocationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);
    private final LocationProperties locationProperties;

    public LocationController(LocationProperties locationProperties) {
        this.locationProperties = locationProperties;
    }

    @RequestMapping("/cities")
    public Set<CityDto> getCities() {
        LOGGER.info("Retrieving cities");

        return locationProperties.getCities();
    }
}
