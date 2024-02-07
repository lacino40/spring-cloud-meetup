package com.sonalake.meetup.service.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ComboOption {
    private String text;
    private String value;
}
