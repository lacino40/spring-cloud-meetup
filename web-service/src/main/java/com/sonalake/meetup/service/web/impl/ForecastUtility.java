package com.sonalake.meetup.service.web.impl;

import com.sonalake.meetup.service.web.dto.CityDto;
import com.sonalake.meetup.service.web.dto.ComboOption;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Stream;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toCollection;

public abstract class ForecastUtility {
    protected Stream<CityDto> sortCitiesById(CityDto[] cityDTOs) {
        return Arrays
                .stream(cityDTOs)
                .collect(toCollection(this::sortedByCityId))
                .stream();
    }

    private TreeSet<CityDto> sortedByCityId() {
        return new TreeSet<>(comparingLong(CityDto::getId));
    }

    protected ComboOption toComboOption(CityDto cityDto) {
        return ComboOption.of(cityDto.getName(), cityDto.getQuery());
    }
}
