package com.sonalake.meetup.service.web.dto;

import lombok.Data;

@Data
public class LocationDto {
    private Long id;
    private String name;
    private String query;
}