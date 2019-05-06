package com.gporres.mercadolibre.galaxytest.service;

import com.gporres.mercadolibre.galaxytest.model.entities.WeatherForecast;
import com.gporres.mercadolibre.galaxytest.rest.dto.Summary;

public interface WeatherForecastService {

    WeatherForecast findByDay(final Integer day) throws Exception;

    Summary calculateWeatherSummary();

    void predictAndStoreWeatherForecast(final Integer fromDay, final Integer toDay);
}
