package com.sonalake.meetup.service.web;

import org.springframework.ui.Model;

public interface WebService {
    WebService addLocationsToModel(Model model);
    WebService addWeatherToModel(Model model);
    String template(String templateName);
}
