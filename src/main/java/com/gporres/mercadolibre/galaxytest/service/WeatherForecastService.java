package com.gporres.mercadolibre.galaxytest.service;

import com.gporres.mercadolibre.galaxytest.model.entities.WeatherForecast;

public interface WeatherForecastService {

    WeatherForecast findByDay(final Integer day) throws Exception;

    void predictAndStoreWeatherForecast(final Integer fromDay, final Integer toDay);
}
