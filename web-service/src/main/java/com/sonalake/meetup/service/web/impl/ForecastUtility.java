package com.sonalake.meetup.service.web.impl;

import com.sonalake.meetup.service.web.dto.CityDto;
import com.sonalake.meetup.service.web.dto.ComboOption;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.stream.Collectors.toCollection;

public abstract class ForecastUtility {

    protected Set<ComboOption> getCitiesComboOptions(CityDto[] cityDTOs) {

        return Arrays
                .stream(cityDTOs)
                .map(this::toComboOption)
                .collect(toCollection(LinkedHashSet::new));
    }

    protected ComboOption toComboOption(CityDto cityDto) {
        return ComboOption.of(cityDto.getName(), cityDto.getQuery());
    }
}
