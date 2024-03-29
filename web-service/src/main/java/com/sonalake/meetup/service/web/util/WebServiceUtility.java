package com.sonalake.meetup.service.web.util;

import com.sonalake.meetup.service.web.dto.ComboOption;
import com.sonalake.meetup.service.web.dto.LocationDto;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.stream.Collectors.toCollection;

public abstract class WebServiceUtility {

    protected Set<ComboOption> getLocationsComboOptions(LocationDto[] locationDTOs) {
        Set<LocationDto> locations = Arrays
                                           .stream(locationDTOs)
                                            .collect(toCollection(LinkedHashSet::new));

        return getLocationsComboOptions(locations);
    }

    protected Set<ComboOption> getLocationsComboOptions(Set<LocationDto> locationDTOs) {

        return locationDTOs
                .stream()
                .map(this::toComboOption)
                .collect(toCollection(LinkedHashSet::new));
    }

    protected ComboOption toComboOption(LocationDto cityDto) {
        return ComboOption.of(cityDto.getName(), cityDto.getQuery());
    }
}
