package com.gporres.mercadolibre.galaxytest.service.impl;

import com.gporres.mercadolibre.galaxytest.model.entities.WeatherForecast;
import com.gporres.mercadolibre.galaxytest.model.enums.WeatherTypeEnum;
import com.gporres.mercadolibre.galaxytest.operations.GalaxyOperations;
import com.gporres.mercadolibre.galaxytest.repository.WeatherForecastRepository;
import com.gporres.mercadolibre.galaxytest.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.gporres.mercadolibre.galaxytest.model.enums.WeatherTypeEnum.WET;

public class WeatherForecastServiceImpl implements WeatherForecastService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherForecastServiceImpl.class);

    @Autowired
    private WeatherForecastRepository weatherForecastRepository;

    @Autowired
    private GalaxyOperations galaxyOperations;

    @Override
    public WeatherForecast findByDay(final Integer day) throws Exception {
        final Optional<WeatherForecast> weatherForecast = weatherForecastRepository.findByDay(day);

        if(!weatherForecast.isPresent()) {
            logger.debug("Prediction for day %s not found.", day);
            throw new Exception("Prediction for day " + day + " not found.");
        }

        return weatherForecast.get();
    }

    @Override
    public void predictAndStoreWeatherForecast(final Integer fromDay, final Integer toDay) {
        final List<WeatherForecast> weatherForecasts = galaxyOperations.predictWeather(fromDay, toDay).entrySet().stream().map(entry -> {

            final WeatherForecast weatherForecast = new WeatherForecast();
            weatherForecast.setDay(entry.getKey());
            weatherForecast.setWeather(entry.getValue());
            weatherForecast.setRainPercentage(Double.valueOf(0));
            weatherForecast.setPlanetsTrianglePerimeter(calculatePlanetsTrianglePerimeter(weatherForecast.getWeather(), weatherForecast.getDay()));

            return weatherForecast;
        }).collect(Collectors.toList());

        weatherForecastRepository.saveAll(weatherForecasts);
        updateRainPercentage();
    }

    private Double calculatePlanetsTrianglePerimeter(final WeatherTypeEnum weatherTypeEnum, final Integer day) {
        if(WET.equals(weatherTypeEnum)) {
            return galaxyOperations.calculatePlanetsTrianglePerimeter(day);
        } else {
            return Double.valueOf(0);
        }
    }

    private void updateRainPercentage() {
        final WeatherForecast topRainWeatherForecast = weatherForecastRepository.findTopByOrderByPlanetsTrianglePerimeterDesc();

        final Double maxTrianglePerimeter = topRainWeatherForecast.getPlanetsTrianglePerimeter();

        for(final WeatherForecast weatherForecast : weatherForecastRepository.findAllByWeather(WET)) {
            weatherForecast.setRainPercentage((weatherForecast.getPlanetsTrianglePerimeter() * 100) / maxTrianglePerimeter);
            weatherForecastRepository.save(weatherForecast);
        }

        topRainWeatherForecast.setRainPercentage(Double.valueOf(100));
        weatherForecastRepository.save(topRainWeatherForecast);
    }
}
