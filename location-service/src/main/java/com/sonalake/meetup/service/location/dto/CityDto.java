package com.sonalake.meetup.service.location.dto;

import lombok.Data;

@Data
public class CityDto {
    private Long id;
    private String name;
    private String query;
}