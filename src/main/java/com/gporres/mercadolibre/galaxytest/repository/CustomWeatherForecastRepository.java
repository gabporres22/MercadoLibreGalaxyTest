package com.gporres.mercadolibre.galaxytest.repository;

import com.gporres.mercadolibre.galaxytest.model.entities.WeatherForecast;
import com.gporres.mercadolibre.galaxytest.model.enums.WeatherTypeEnum;

import java.util.List;
import java.util.Optional;

public interface CustomWeatherForecastRepository {
    Optional<WeatherForecast> findByDay(final Integer day);

    List<WeatherForecast> findAllByWeather(final WeatherTypeEnum weatherTypeEnum);

    WeatherForecast findTopByOrderByPlanetsTrianglePerimeterDesc();
}
