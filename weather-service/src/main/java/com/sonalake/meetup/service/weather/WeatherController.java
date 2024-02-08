package com.sonalake.meetup.service.weather;

import com.sonalake.meetup.service.weather.dto.WeatherDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping
    public WeatherDto getWeather(@RequestParam String query) {

        return weatherService.getWeather(query);
    }
}
