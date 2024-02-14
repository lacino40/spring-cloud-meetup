package com.sonalake.meetup.service.web.basic;

import com.sonalake.meetup.service.web.WebProperties;
import com.sonalake.meetup.service.web.WebService;
import com.sonalake.meetup.service.web.dto.ComboOption;
import com.sonalake.meetup.service.web.dto.LocationDto;
import com.sonalake.meetup.service.web.dto.WeatherDto;
import com.sonalake.meetup.service.web.util.WebServiceUtility;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Set;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.exception.ExceptionUtils.getStackTrace;

@AllArgsConstructor
public class WebServiceBasicImpl extends WebServiceUtility implements WebService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceBasicImpl.class);
    private static URI LOCATION_URI;
    private final RestTemplate restTemplate;
    private final WebProperties webProperties;

    @PostConstruct
    public void intUrls() {
        LOCATION_URI = webProperties.getLocationURI();
    }

    @Override
    public WebService addLocationsToModel(Model model) {
        try {

            ResponseEntity<LocationDto[]> response = restTemplate.getForEntity(LOCATION_URI, LocationDto[].class);
            Set<ComboOption> locationsComboOptions = getLocationsComboOptions(requireNonNull(response.getBody()));

            model.addAttribute("locationsOptions", locationsComboOptions);

        } catch (Exception e) {
            /*
             * Basic exception handling to provide fallback functionality
             */
            addErrorAttributes(
                    "location-service is not available",
                    model,
                    getStackTrace(e)
            );
        }

        return this;
    }

    @Override
    public WebService addWeatherToModel(Model model) {
        String selectedLocation = (String) model.asMap().get("selectedLocation");
        boolean emptyLocation = isBlank(selectedLocation);

        model.addAttribute("selectedLocation", selectedLocation);

        if(emptyLocation) {
            model.addAttribute("showWeatherDetails", FALSE);
            return this;
        }

        try {

            URI weatherURI = webProperties.getWeatherURI(selectedLocation);
            ResponseEntity<WeatherDto> response = restTemplate.getForEntity(weatherURI, WeatherDto.class);
            model.addAttribute("weatherDto", requireNonNull(response.getBody()));
            model.addAttribute("showWeatherDetails", TRUE);

        } catch (Exception e) {
            /*
             * Basic exception handling to provide fallback functionality
             */
            addErrorAttributes(
                    "weather-service is not available",
                    model,
                    getStackTrace(e)
            );
        }

        return this;
    }

    @Override
    public String template(String templateName) {
        return templateName;
    }

    private void addErrorAttributes(String errorMessage, Model model, String errorStackTrace) {
        LOGGER.error(errorMessage);

        model.addAttribute("isError", TRUE);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("errorStackTrace", errorStackTrace);
        model.addAttribute("showWeatherDetails", FALSE);
    }
}
