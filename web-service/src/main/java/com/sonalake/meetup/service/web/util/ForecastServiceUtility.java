package com.sonalake.meetup.service.web.util;

import com.sonalake.meetup.service.web.dto.ComboOption;
import com.sonalake.meetup.service.web.dto.LocationDto;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.stream.Collectors.toCollection;

public abstract class ForecastServiceUtility {

    protected Set<ComboOption> getLocationsComboOptions(LocationDto[] locationDTOs) {

        return Arrays
                .stream(locationDTOs)
                .map(this::toComboOption)
                .collect(toCollection(LinkedHashSet::new));
    }

    protected ComboOption toComboOption(LocationDto cityDto) {
        return ComboOption.of(cityDto.getName(), cityDto.getQuery());
    }
}
