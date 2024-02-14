package com.sonalake.meetup.service.web.cloud.gateway;

import com.sonalake.meetup.service.web.WebService;
import com.sonalake.meetup.service.web.dto.ComboOption;
import com.sonalake.meetup.service.web.dto.LocationDto;
import com.sonalake.meetup.service.web.dto.WeatherDto;
import com.sonalake.meetup.service.web.util.WebServiceUtility;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.ui.Model;

import java.util.Set;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.apache.commons.lang.StringUtils.isBlank;

@EnableFeignClients
@AllArgsConstructor
public class WebServiceGatewayImpl extends WebServiceUtility implements WebService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceGatewayImpl.class);

    private final GatewayFeignClient gatewayFeignClient;

    @Override
    public WebService addLocationsToModel(Model model) {
        Set<LocationDto> locations = gatewayFeignClient.getLocations();

        if(locations.isEmpty()) {
            addErrorAttributes("location-service is not available", model, null);
            return this;
        }

        Set<ComboOption> locationsComboOptions = getLocationsComboOptions(locations);

        model.addAttribute("locationsOptions", locationsComboOptions);

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

        WeatherDto weather = gatewayFeignClient.getWeather(selectedLocation);

        model.addAttribute("weatherDto", weather);
        model.addAttribute("showWeatherDetails", TRUE);

        if(weather.isError()) {
            addErrorAttributes(
                    "weather-service is not available",
                    model,
                    weather.getError().getErrorStackTrace()
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
