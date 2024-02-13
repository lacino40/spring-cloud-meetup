package com.sonalake.meetup.service.web;

import org.springframework.ui.Model;

public interface WebService {
    WebService addCitiesToModel(Model model);
    WebService addForecastToModel(Model model);
    String template(String templateName);
}
