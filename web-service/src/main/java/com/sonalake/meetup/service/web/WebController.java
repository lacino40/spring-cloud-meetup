package com.sonalake.meetup.service.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/forecast")
public class WebController {
    private final WebService webService;

    @GetMapping
    public String getForecast(Model model) {

        return webService
                .addLocationsToModel(model)
                .addWeatherToModel(model)
                .template("index");
    }

    @PostMapping("/submit")
    public String submit(@RequestParam("selectedLocation") String selectedLocation, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("selectedLocation", selectedLocation);

        return "redirect:/forecast";
    }
}
