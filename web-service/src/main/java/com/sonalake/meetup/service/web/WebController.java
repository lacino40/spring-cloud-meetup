package com.sonalake.meetup.service.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
public class WebController {
    private final ForecastService forecastService;

    @GetMapping("/forecast")
    public String getMainPage(Model model) {

        return forecastService
                .addCitiesToModel(model)
                .addForecastToModel(model)
                .template("index");
    }

    @PostMapping("/submit")
    public String submit(@RequestParam("selectedCity") String selectedCity, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("selectedCity", selectedCity);

        return "redirect:/forecast";
    }
}
