package com.sonalake.meetup.service.location;

import com.sonalake.meetup.service.location.dto.CityDto;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toCollection;

public abstract class LocationUtility {
    protected Set<CityDto> sortCitiesById(Set<CityDto> cityDTOs) {

        return cityDTOs
                .stream()
                .sorted(comparing(CityDto::getId))
                .collect(toCollection(LinkedHashSet::new));
    }
}
