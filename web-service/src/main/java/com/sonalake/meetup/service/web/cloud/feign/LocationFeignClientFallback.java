package com.sonalake.meetup.service.web.cloud.feign;

import com.sonalake.meetup.service.web.dto.LocationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocationFeignClientFallback implements LocationFeignClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationFeignClientFallback.class);

    @Override
    public LocationDto[] getLocations() {
        LOGGER.debug("location-service not available");

        return null;
    }
}
