package com.sonalake.meetup.service.weather;

import com.sonalake.meetup.service.weather.dto.OpenWeatherDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping
    public OpenWeatherDto getWeather(@RequestParam String query) throws IOException {

        return weatherService.getWeather(query);
    }
}
