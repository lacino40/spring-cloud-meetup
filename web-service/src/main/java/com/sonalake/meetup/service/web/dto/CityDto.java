package com.sonalake.meetup.service.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto {
    private Long id;
    private String name;
    private String query;
}