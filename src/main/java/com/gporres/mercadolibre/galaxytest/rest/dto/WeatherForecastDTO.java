package com.gporres.mercadolibre.galaxytest.rest.dto;

import com.gporres.mercadolibre.galaxytest.model.entities.WeatherForecast;
import com.gporres.mercadolibre.galaxytest.model.enums.WeatherTypeEnum;

import java.io.Serializable;

public class WeatherForecastDTO implements Serializable {
    private Integer dia;
    private WeatherTypeEnum weatherTypeEnum;
    private Double rainPercentage;

    public WeatherForecastDTO(final WeatherForecast weatherForecast) {
        dia = weatherForecast.getDay();
        weatherTypeEnum = weatherForecast.getWeather();
        rainPercentage = weatherForecast.getRainPercentage();
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public WeatherTypeEnum getWeatherTypeEnum() {
        return weatherTypeEnum;
    }

    public void setWeatherTypeEnum(WeatherTypeEnum weatherTypeEnum) {
        this.weatherTypeEnum = weatherTypeEnum;
    }

    public Double getRainPercentage() {
        return rainPercentage;
    }

    public void setRainPercentage(Double rainPercentage) {
        this.rainPercentage = rainPercentage;
    }
}
