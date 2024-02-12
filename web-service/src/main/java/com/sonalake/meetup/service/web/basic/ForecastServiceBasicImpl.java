package com.sonalake.meetup.service.web.basic;

import com.sonalake.meetup.service.web.ForecastService;
import com.sonalake.meetup.service.web.WebProperties;
import com.sonalake.meetup.service.web.dto.ComboOption;
import com.sonalake.meetup.service.web.dto.LocationDto;
import com.sonalake.meetup.service.web.dto.OpenWeatherDto;
import com.sonalake.meetup.service.web.util.ForecastServiceUtility;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang.BooleanUtils.isFalse;
import static org.apache.commons.lang.StringUtils.isBlank;

@AllArgsConstructor
public class ForecastServiceBasicImpl extends ForecastServiceUtility implements ForecastService {
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
        boolean emptyLocation = isBlank(selectedLocation);

        model.addAttribute("showWeatherDetails", isFalse(emptyLocation));
        model.addAttribute("selectedLocation", selectedLocation);

        if(emptyLocation) {
            return this;
        }

        URI weatherURI = webProperties.getWeatherURI(selectedLocation);
        ResponseEntity<OpenWeatherDto> response = restTemplate.getForEntity(weatherURI, OpenWeatherDto.class);

        model.addAttribute("weatherDto", requireNonNull(response.getBody()));

        return this;
    }

    @Override
    public String template(String templateName) {
        return templateName;
    }
}