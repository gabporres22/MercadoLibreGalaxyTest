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
    private Double planetsTrianglePerimeter;

    public Integer getId() {
        return id;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(final Integer day) {
        this.day = day;
    }

    public WeatherTypeEnum getWeather() {
        return weather;
    }

    public void setWeather(final WeatherTypeEnum weather) {
        this.weather = weather;
    }

    public Double getRainPercentage() {
        return rainPercentage;
    }

    public void setRainPercentage(final Double rainPercentage) {
        this.rainPercentage = rainPercentage;
    }

    public Double getPlanetsTrianglePerimeter() {
        return planetsTrianglePerimeter;
    }

    public void setPlanetsTrianglePerimeter(final Double planetsTrianglePerimeter) {
        this.planetsTrianglePerimeter = planetsTrianglePerimeter;
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "day=" + day +
                ", weather=" + weather +
                ", rainPercentage=" + rainPercentage +
                ", planetsTrianglePerimeter=" + planetsTrianglePerimeter +
                '}';
    }
}