package com.sonalake.meetup.service.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/forecast")
    public String getForecast(Model model) {
        return "index";
    }
}
