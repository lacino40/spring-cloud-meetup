package com.sonalake.meetup.service.web.impl;

import com.sonalake.meetup.service.web.ForecastService;
import com.sonalake.meetup.service.web.WebProperties;
import com.sonalake.meetup.service.web.dto.CityDto;
import com.sonalake.meetup.service.web.dto.ComboOption;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.lang.StringUtils.isBlank;

@AllArgsConstructor
public class ForecastServiceImpl extends ForecastUtility implements ForecastService {
    private static URI CITIES_URI;
    private final RestTemplate restTemplate;
    private final WebProperties webProperties;

    @PostConstruct
    public void intUrls() {
        CITIES_URI = webProperties.getLocationUrlFor("cities");
    }

    @Override
    public ForecastService addCitiesToModel(Model model) {
        Set<ComboOption> citiesComboOptions = getCitiesComboOptions();

        model.addAttribute("cityOptions", citiesComboOptions);
        return this;
    }

    @Override
    public ForecastService addForecastToModel(Model model) {
        String selectedCity = (String) model.asMap().get("selectedCity");

        model.addAttribute("isCitySelected", isBlank(selectedCity));
        model.addAttribute("selectedCity", selectedCity);

        return this;
    }

    @Override
    public String template(String templateName) {
        return templateName;
    }

    private Set<ComboOption> getCitiesComboOptions() {
        ResponseEntity<CityDto[]> response = restTemplate.getForEntity(CITIES_URI, CityDto[].class);

        return sortCitiesById(requireNonNull(response.getBody()))
                .map(this::toComboOption)
                .collect(toSet());
    }
}
