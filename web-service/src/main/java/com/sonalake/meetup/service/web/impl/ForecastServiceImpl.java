package com.sonalake.meetup.service.web.impl;

import com.sonalake.meetup.service.web.ForecastService;
import com.sonalake.meetup.service.web.WebProperties;
import com.sonalake.meetup.service.web.dto.ComboOption;
import com.sonalake.meetup.service.web.dto.LocationDto;
import com.sonalake.meetup.service.web.dto.OpenWeatherDto;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang.StringUtils.isBlank;

@AllArgsConstructor
public class ForecastServiceImpl extends ForecastUtility implements ForecastService {
    private static URI LOCATION_URI;
    private final RestTemplate restTemplate;
    private final WebProperties webProperties;

    @PostConstruct
    public void intUrls() {
        LOCATION_URI = webProperties.getLocationURI();
    }

    @Override
    public ForecastService addCitiesToModel(Model model) {
        ResponseEntity<LocationDto[]> response = restTemplate.getForEntity(LOCATION_URI, LocationDto[].class);
        Set<ComboOption> locationsComboOptions = getLocationsComboOptions(requireNonNull(response.getBody()));

        model.addAttribute("locationsOptions", locationsComboOptions);

        return this;
    }

    @Override
    public ForecastService addForecastToModel(Model model) {
        String selectedLocation = (String) model.asMap().get("selectedLocation");
        boolean isSelectedLocationBlank = isBlank(selectedLocation);

        model.addAttribute("isLocationSelected", isSelectedLocationBlank);
        model.addAttribute("selectedLocation", selectedLocation);

        if(isSelectedLocationBlank) {
            return this;
        }

        URI weatherURI = webProperties.getWeatherURI(selectedLocation);
        ResponseEntity<OpenWeatherDto> response = restTemplate.getForEntity(weatherURI, OpenWeatherDto.class);

        model.addAttribute("weatherDto", requireNonNull(response));

        return this;
    }

    @Override
    public String template(String templateName) {
        return templateName;
    }
}
