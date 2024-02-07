package com.sonalake.meetup.service.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class WebController {
    private final ForecastService forecastService;

    @GetMapping("/forecast")
    public String getMainPage(Model model) {

        return forecastService
                .addCitiesToModel(model)
                .template("index");
    }

    @PostMapping("/submit")
    public String submit(Model model) {

        return forecastService
                .addForecastToModel(model)
                .template("index");

    }
}
