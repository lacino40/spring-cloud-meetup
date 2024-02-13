package com.sonalake.meetup.service.weather;

import com.sonalake.meetup.service.weather.dto.OpenWeatherDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/weather")
public class WeatherController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);

    private final WeatherService weatherService;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    @GetMapping
    public OpenWeatherDto getWeather(@RequestParam String query) {
        return circuitBreakerFactory
                .create("open-weather-api")
                .run(
                        () -> requestOpenWeatherApi(query),
                        throwable -> {
                            throw new RuntimeException(throwable);
                        }
                );
    }

    private OpenWeatherDto requestOpenWeatherApi(String query) {
        try {

            return weatherService.requestOpenWeatherApi(query);
        } catch (Exception e) {
            LOGGER.error("open-weather-api is not available for query {}: {}", query, e.getMessage());
            return new OpenWeatherDto().withError();
        }
    }
}
