package com.gporres.mercadolibre.galaxytest.model.entities;

import com.gporres.mercadolibre.galaxytest.model.enums.WeatherTypeEnum;

import javax.persistence.*;

@Entity
@Table
public class WeatherForecast {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer day;

    @Column(nullable = false)
    private WeatherTypeEnum weather;

    @Column
    private Double rainPercentage;

    @Column(nullable = false)
    private Double planetTrianglePerimeter;

    public Integer getId() {
        return id;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public WeatherTypeEnum getWeather() {
        return weather;
    }

    public void setWeather(WeatherTypeEnum weather) {
        this.weather = weather;
    }

    public Double getRainPercentage() {
        return rainPercentage;
    }

    public void setRainPercentage(Double rainPercentage) {
        this.rainPercentage = rainPercentage;
    }

    public Double getPlanetTrianglePerimeter() {
        return planetTrianglePerimeter;
    }

    public void setPlanetTrianglePerimeter(Double planetTrianglePerimeter) {
        this.planetTrianglePerimeter = planetTrianglePerimeter;
    }
}