package com.gporres.mercadolibre.galaxytest.rest;

import com.google.common.collect.Lists;
import com.gporres.mercadolibre.galaxytest.model.entities.WeatherForecast;
import com.gporres.mercadolibre.galaxytest.model.enums.WeatherTypeEnum;
import com.gporres.mercadolibre.galaxytest.repository.WeatherForecastRepository;
import com.gporres.mercadolibre.galaxytest.rest.dto.Summary;
import com.gporres.mercadolibre.galaxytest.rest.dto.WeatherForecastDTO;
import com.gporres.mercadolibre.galaxytest.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;

@RestController
@RequestMapping("/api/clima")
public class WeatherForecastServiceController {
    private static final Logger logger = LoggerFactory.getLogger(WeatherForecastServiceController.class);

    @Autowired
    private WeatherForecastService weatherForecastService;

    @Autowired
    private WeatherForecastRepository weatherForecastRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<WeatherForecastDTO> findWeatherForecastByDay(@RequestParam("dia") Integer day) throws Exception {
        final WeatherForecast weatherForecast = weatherForecastService.findByDay(day);

        return new ResponseEntity<>(new WeatherForecastDTO(weatherForecast), HttpStatus.OK);
    }

    @RequestMapping(value="/summary", method = RequestMethod.GET)
    public ResponseEntity<Summary> calculateSummary() {
        final ArrayList<WeatherForecast> weatherForecasts = Lists.newArrayList(weatherForecastRepository.findAll());

        LinkedList<WeatherTypeEnum> weatherPeriods = weatherForecasts.stream().map(o -> o.getWeather()).collect(LinkedList<WeatherTypeEnum>::new,
                (list, elem) -> {
                    if (list.isEmpty() || !elem.equals(list.getLast()))
                        list.add(elem);
                }, LinkedList<WeatherTypeEnum>::addAll);

        final Summary summary = new Summary();

        final WeatherForecast topByOrderByPlanetsTrianglePerimeterDesc = weatherForecastRepository.findTopByOrderByPlanetsTrianglePerimeterDesc();

        summary.setRainPeriods(weatherPeriods.stream().filter(WeatherTypeEnum.WET::equals).count());
        summary.setDryPeriods(weatherPeriods.stream().filter(WeatherTypeEnum.DRY::equals).count());
        summary.setOptimalPeriods(weatherPeriods.stream().filter(WeatherTypeEnum.OPTIMAL_CONDITIONS::equals).count());
        summary.setUnknowDays(weatherPeriods.stream().filter(WeatherTypeEnum.UNKNOW::equals).count());
        summary.setMaximumRainingDay(topByOrderByPlanetsTrianglePerimeterDesc.getDay().intValue());

        return new ResponseEntity<>(summary, HttpStatus.OK);
    }
}
