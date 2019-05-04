package com.gporres.mercadolibre.galaxytest.operations;

import com.gporres.mercadolibre.galaxytest.model.Galaxy;
import com.gporres.mercadolibre.galaxytest.model.enums.WeatherTypeEnum;
import com.gporres.mercadolibre.galaxytest.operations.geometric.Line;
import com.gporres.mercadolibre.galaxytest.operations.geometric.Triangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class GalaxyOperations {
    @Autowired
    private PlanetOperations planetOperations;

    @Autowired
    private Galaxy galaxy;

    @Autowired
    private PlanetsCoordinates planetsCoordinates;

    @Autowired
    private LineOperations lineOperations;

    @Autowired
    private TriangleOperations triangleOperations;

    public Map<Integer, WeatherTypeEnum> predictWeather(final Integer dayFrom, final Integer dayTo) {
        return IntStream.rangeClosed(dayFrom, dayTo).boxed().collect(
                Collectors.toMap(Function.identity(), this::predictWeather));
    }

    public Double calculatePlanetsTrianglePerimeter(final Integer day) {
        planetsCoordinates.calculatePlanetsCoordinates(day);

        final Line ferengiBetasoideline = new Line(planetsCoordinates.getFerengiCoordinates(), planetsCoordinates.getBetasoideCoordinates());

        if(lineOperations.isPointInLine(ferengiBetasoideline, planetsCoordinates.getVulcanoCoordinates()))
            return Double.valueOf(0);

        final Triangle triangle = new Triangle(planetsCoordinates.getFerengiCoordinates(), planetsCoordinates.getVulcanoCoordinates(), planetsCoordinates.getBetasoideCoordinates());

        return triangleOperations.calculatePerimeter(triangle);
    }

    private WeatherTypeEnum predictWeather(final Integer day) {
        planetsCoordinates.calculatePlanetsCoordinates(day);

        if(checkDryWeather())
            return WeatherTypeEnum.DRY;

        if(checkWetWeather())
            return WeatherTypeEnum.WET;

        if(checkOptimalWeather())
            return WeatherTypeEnum.OPTIMAL_CONDITIONS;

        return WeatherTypeEnum.UNKNOW;
    }

    private Boolean checkDryWeather() {
        final Line sunFerengiLine = new Line(galaxy.getSunCoordinates(), planetsCoordinates.getFerengiCoordinates());

        if(lineOperations.isPointInLine(sunFerengiLine, planetsCoordinates.getVulcanoCoordinates()) && lineOperations.isPointInLine(sunFerengiLine, planetsCoordinates.getBetasoideCoordinates()))
            return true;

        return false;
    }

    private Boolean checkWetWeather() {
        final Triangle triangle = new Triangle(planetsCoordinates.getFerengiCoordinates(), planetsCoordinates.getVulcanoCoordinates(), planetsCoordinates.getBetasoideCoordinates());

        if(triangleOperations.isPointInside(triangle, galaxy.getSunCoordinates()))
            return true;

        return false;
    }

    private Boolean checkOptimalWeather() {
        final Line ferengiBetasoideLine = new Line(planetsCoordinates.getFerengiCoordinates(), planetsCoordinates.getBetasoideCoordinates());

        if (lineOperations.isPointInLine(ferengiBetasoideLine, planetsCoordinates.getVulcanoCoordinates()))
            return true;

        return false;
    }
}
