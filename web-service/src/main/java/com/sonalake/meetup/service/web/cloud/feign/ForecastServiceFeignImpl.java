package com.sonalake.meetup.service.web.cloud.feign;

import com.sonalake.meetup.service.web.ForecastService;
import com.sonalake.meetup.service.web.dto.ComboOption;
import com.sonalake.meetup.service.web.dto.LocationDto;
import com.sonalake.meetup.service.web.dto.OpenWeatherDto;
import com.sonalake.meetup.service.web.util.ForecastServiceUtility;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.ui.Model;

import java.util.Set;

import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;
import static org.apache.commons.lang.BooleanUtils.isFalse;
import static org.apache.commons.lang.StringUtils.isBlank;

@EnableFeignClients
@AllArgsConstructor
public class ForecastServiceFeignImpl extends ForecastServiceUtility implements ForecastService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastServiceFeignImpl.class);

    private final LocationFeignClient locationFeignClient;
    private final WeatherFeignClient webFeignClient;

    @Override
    public ForecastService addCitiesToModel(Model model) {
        LocationDto[] locations = locationFeignClient.getLocations();

        if(isNull(locations)) {
            addErrorAttributes("location-service is not available", model, null);
            return this;
        }

        Set<ComboOption> locationsComboOptions = getLocationsComboOptions(locations);

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

        OpenWeatherDto weather = webFeignClient.getWeather(selectedLocation);

        model.addAttribute("weatherDto", weather);
        model.addAttribute("showWeatherDetails", weather.isOk());

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
    }
}
