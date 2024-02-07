package com.sonalake.meetup.service.web;

import org.springframework.ui.Model;

public interface ForecastService {
    ForecastService addCitiesToModel(Model model);
    ForecastService addForecastToModel(Model model);
    String template(String templateName);
}
