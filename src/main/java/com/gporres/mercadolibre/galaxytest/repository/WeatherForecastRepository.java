package com.gporres.mercadolibre.galaxytest.repository;

import com.gporres.mercadolibre.galaxytest.model.entities.WeatherForecast;
import org.springframework.data.repository.CrudRepository;

public interface WeatherForecastRepository extends CrudRepository<WeatherForecast, Integer>, CustomWeatherForecastRepository {

}