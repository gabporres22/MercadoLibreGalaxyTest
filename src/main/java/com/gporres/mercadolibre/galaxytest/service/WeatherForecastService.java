package com.gporres.mercadolibre.galaxytest.service;

import com.gporres.mercadolibre.galaxytest.model.entities.WeatherForecast;
import com.gporres.mercadolibre.galaxytest.rest.dto.Summary;

import java.util.Optional;

public interface WeatherForecastService {

    Optional<WeatherForecast> findByDay(final Integer day);

    Summary calculateWeatherSummary();

    void predictAndStoreWeatherForecast(final Integer fromDay, final Integer toDay);
}
