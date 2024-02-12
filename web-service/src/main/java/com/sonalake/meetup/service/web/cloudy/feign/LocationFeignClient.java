package com.sonalake.meetup.service.web.cloudy.feign;

import com.sonalake.meetup.service.web.dto.LocationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Feign client to retrieve locations from the discovery service
 * List all available end points here
 */
@FeignClient("location-service")
public interface LocationFeignClient {

    @GetMapping("/location")
    LocationDto[] getLocations();
}
