package com.sonalake.meetup.service.web.cloud.feign;

import com.sonalake.meetup.service.web.dto.LocationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashSet;

/**
 * Feign client to retrieve locations from the discovery service
 * List all available end points here
 */
@FeignClient(value = "location-service", fallback = LocationFeignClientFallback.class)
public interface LocationFeignClient {

    @GetMapping("/location")
    LinkedHashSet<LocationDto> getLocations();
}
