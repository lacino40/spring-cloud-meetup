package com.sonalake.meetup.service.location;

import com.sonalake.meetup.service.location.dto.LocationDto;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toCollection;

public abstract class LocationUtility {
    protected Set<LocationDto> sortLocationById(Set<LocationDto> locationDTOs) {

        return locationDTOs
                .stream()
                .sorted(comparing(LocationDto::getId))
                .collect(toCollection(LinkedHashSet::new));
    }
}
