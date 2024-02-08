package com.sonalake.meetup.service.web.impl;

import com.sonalake.meetup.service.web.dto.ComboOption;
import com.sonalake.meetup.service.web.dto.LocationDto;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.stream.Collectors.toCollection;

public abstract class ForecastUtility {

    protected Set<ComboOption> locationsComboOptions(LocationDto[] locationDTOs) {

        return Arrays
                .stream(locationDTOs)
                .map(this::toComboOption)
                .collect(toCollection(LinkedHashSet::new));
    }

    protected ComboOption toComboOption(LocationDto cityDto) {
        return ComboOption.of(cityDto.getName(), cityDto.getQuery());
    }
}
