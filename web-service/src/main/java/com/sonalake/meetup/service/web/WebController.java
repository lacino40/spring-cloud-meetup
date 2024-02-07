package com.sonalake.meetup.service.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WebController {
    private final ForecastService forecastService;

    @GetMapping("/forecast")
    public String getForecast(Model model) {
        forecastService.addCitiesToModel(model);

        return "index";
    }
}
