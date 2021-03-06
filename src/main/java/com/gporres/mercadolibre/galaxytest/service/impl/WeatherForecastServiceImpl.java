package com.gporres.mercadolibre.galaxytest.service.impl;

import com.google.common.collect.Lists;
import com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper;
import com.gporres.mercadolibre.galaxytest.model.entities.WeatherForecast;
import com.gporres.mercadolibre.galaxytest.model.enums.WeatherTypeEnum;
import com.gporres.mercadolibre.galaxytest.operations.GalaxyOperations;
import com.gporres.mercadolibre.galaxytest.repository.WeatherForecastRepository;
import com.gporres.mercadolibre.galaxytest.rest.dto.Summary;
import com.gporres.mercadolibre.galaxytest.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper.DIFFERENT_FROM_ZERO;
import static com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper.GREATER_THAN_FIRST_PARAM;
import static com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper.GREATER_THAN_ZERO;
import static com.gporres.mercadolibre.galaxytest.model.enums.WeatherTypeEnum.WET;

public class WeatherForecastServiceImpl implements WeatherForecastService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherForecastServiceImpl.class);

    @Autowired
    private WeatherForecastRepository weatherForecastRepository;

    @Autowired
    private GalaxyOperations galaxyOperations;

    @Override
    public Optional<WeatherForecast> findByDay(final @NotNull Integer day) {
        PreconditionsHelper.checkNotNullAndArgument(day, "Day", day >= 0, GREATER_THAN_ZERO);

        logger.debug("Find Weather at day {}", day);

        final Optional<WeatherForecast> weatherForecast = weatherForecastRepository.findByDay(day);

        if(!weatherForecast.isPresent()) {
            logger.debug("Prediction for day {} not found.", day);
            return Optional.empty();
        }

        return weatherForecast;
    }

    @Override
    public void predictAndStoreWeatherForecast(final @NotNull Integer fromDay, @NotNull final Integer toDay) {
        PreconditionsHelper.checkNotNullAndArgument(fromDay, "FromDay",fromDay >= 0, GREATER_THAN_ZERO);
        PreconditionsHelper.checkNotNullAndArgument(toDay, "ToDay",toDay > 0, GREATER_THAN_ZERO);
        PreconditionsHelper.checkArgument(toDay > fromDay, "toDay-fromDay", GREATER_THAN_FIRST_PARAM);

        logger.debug("PredictAndStoreWeatherForecast by range {} - {}", fromDay, toDay);

        final List<WeatherForecast> weatherForecasts = galaxyOperations.predictWeather(fromDay, toDay).entrySet().stream().map(entry -> {

            final WeatherForecast weatherForecast = new WeatherForecast();
            weatherForecast.setDay(entry.getKey());
            weatherForecast.setWeather(entry.getValue());
            weatherForecast.setRainPercentage(Double.valueOf(0));
            weatherForecast.setPlanetsTrianglePerimeter(calculatePlanetsTrianglePerimeter(weatherForecast.getWeather(), weatherForecast.getDay()));

            return weatherForecast;
        }).collect(Collectors.toList());

        logger.debug("Saving {} entities", weatherForecasts.size());

        weatherForecastRepository.saveAll(weatherForecasts);
        updateRainPercentage();
    }

    @Override
    public Summary calculateWeatherSummary() {
        final ArrayList<WeatherForecast> weatherForecasts = Lists.newArrayList(weatherForecastRepository.findAll());

        final LinkedList<WeatherTypeEnum> weatherPeriods = weatherForecasts.stream().map(o -> o.getWeather()).collect(LinkedList<WeatherTypeEnum>::new,
                (list, elem) -> {
                    if (list.isEmpty() || !elem.equals(list.getLast())) list.add(elem);
                }, LinkedList::addAll);

        final WeatherForecast topByOrderByPlanetsTrianglePerimeterDesc = weatherForecastRepository.findTopByOrderByPlanetsTrianglePerimeterDesc();
        final Summary summary = new Summary();

        summary.setRainPeriods(weatherPeriods.stream().filter(WeatherTypeEnum.WET::equals).count());
        summary.setDryPeriods(weatherPeriods.stream().filter(WeatherTypeEnum.DRY::equals).count());
        summary.setOptimalPeriods(weatherPeriods.stream().filter(WeatherTypeEnum.OPTIMAL_CONDITIONS::equals).count());
        summary.setUnknowDays(weatherPeriods.stream().filter(WeatherTypeEnum.UNKNOW::equals).count());
        summary.setMaximumRainingDay(topByOrderByPlanetsTrianglePerimeterDesc.getDay().intValue());

        return summary;
    }

    private Double calculatePlanetsTrianglePerimeter(final WeatherTypeEnum weatherTypeEnum, final @NotNull Integer day) {
        PreconditionsHelper.checkNotNullAndArgument(day, "Day",day >= 0, GREATER_THAN_ZERO);

        if(WET.equals(weatherTypeEnum)) {
            return galaxyOperations.calculatePlanetsTrianglePerimeter(day);
        } else {
            return Double.valueOf(0);
        }
    }

    private void updateRainPercentage() {
        final WeatherForecast topRainWeatherForecast = weatherForecastRepository.findTopByOrderByPlanetsTrianglePerimeterDesc();
        PreconditionsHelper.checkNotNull(topRainWeatherForecast, "TopRainWeatherForecast");

        logger.debug("Update rain percentage by maximum day {}", topRainWeatherForecast.getDay());

        final Double maxTrianglePerimeter = topRainWeatherForecast.getPlanetsTrianglePerimeter();
        PreconditionsHelper.checkArgument(maxTrianglePerimeter != 0, "maxTrianglePerimeter", DIFFERENT_FROM_ZERO);

        for(final WeatherForecast weatherForecast : weatherForecastRepository.findAllByWeather(WET)) {
            weatherForecast.setRainPercentage((weatherForecast.getPlanetsTrianglePerimeter() * 100) / maxTrianglePerimeter);
            weatherForecastRepository.save(weatherForecast);
        }

        topRainWeatherForecast.setRainPercentage(Double.valueOf(100));
        weatherForecastRepository.save(topRainWeatherForecast);
    }
}
