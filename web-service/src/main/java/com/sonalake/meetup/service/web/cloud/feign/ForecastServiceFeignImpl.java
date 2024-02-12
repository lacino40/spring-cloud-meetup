package com.sonalake.meetup.service.web.cloud.feign;

import com.sonalake.meetup.service.web.ForecastService;
import com.sonalake.meetup.service.web.dto.ComboOption;
import com.sonalake.meetup.service.web.dto.LocationDto;
import com.sonalake.meetup.service.web.dto.OpenWeatherDto;
import com.sonalake.meetup.service.web.util.ForecastServiceUtility;
import lombok.AllArgsConstructor;
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
    private final LocationFeignClient locationFeignClient;
    private final WeatherFeignClient webFeignClient;

    @Override
    public ForecastService addCitiesToModel(Model model) {
        LocationDto[] locations = locationFeignClient.getLocations();

        if(isNull(locations)) {
            addErrorAttributes(model, "location-service not available");
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
        model.addAttribute("showWeatherDetails", isFalse(weather.isError()));

        if(weather.isError()) {
            addErrorAttributes(model, "weather-service not available");
        }

        return this;
    }

    @Override
    public String template(String templateName) {
        return templateName;
    }

    private void addErrorAttributes(Model model, String errorMessage) {
        model.addAttribute("isError", TRUE);
        model.addAttribute("errorMessage", errorMessage);
    }
}
