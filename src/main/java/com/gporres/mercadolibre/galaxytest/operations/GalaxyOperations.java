package com.gporres.mercadolibre.galaxytest.operations;

import com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper;
import com.gporres.mercadolibre.galaxytest.model.Galaxy;
import com.gporres.mercadolibre.galaxytest.model.enums.WeatherTypeEnum;
import com.gporres.mercadolibre.galaxytest.operations.geometric.Line;
import com.gporres.mercadolibre.galaxytest.operations.geometric.Triangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper.GREATER_THAN_FIRST_PARAM;
import static com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper.GREATER_THAN_ZERO;

@Component
public class GalaxyOperations {
    private static final Logger logger = LoggerFactory.getLogger(GalaxyOperations.class);

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

    public Map<Integer, WeatherTypeEnum> predictWeather(final @NotNull Integer dayFrom, final @NotNull Integer dayTo) {
        PreconditionsHelper.checkNotNullAndArgument(dayFrom, "DayFrom", dayFrom >= 0, GREATER_THAN_ZERO);
        PreconditionsHelper.checkNotNullAndArgument(dayTo, "DayTo", dayTo > 0, GREATER_THAN_ZERO);
        PreconditionsHelper.checkArgument(dayTo > dayFrom, "dayTo-dayFrom", GREATER_THAN_FIRST_PARAM);

        logger.debug("Predict Weather Range [{} - {}]", dayFrom, dayTo);

        return IntStream.rangeClosed(dayFrom, dayTo).boxed().collect(
                Collectors.toMap(Function.identity(), this::predictWeather));
    }

    public Double calculatePlanetsTrianglePerimeter(final @NotNull Integer day) {
        PreconditionsHelper.checkNotNullAndArgument(day, "Day",day >= 0, GREATER_THAN_ZERO);
        logger.debug("Planets Triangle Perimeter for day {}", day);
        planetsCoordinates.calculatePlanetsCoordinates(day);

        final Line ferengiBetasoideline = new Line(planetsCoordinates.getFerengiCoordinates(), planetsCoordinates.getBetasoideCoordinates());

        if(lineOperations.isPointInLine(ferengiBetasoideline, planetsCoordinates.getVulcanoCoordinates()))
            return Double.valueOf(0);

        final Triangle triangle = new Triangle(planetsCoordinates.getFerengiCoordinates(), planetsCoordinates.getVulcanoCoordinates(), planetsCoordinates.getBetasoideCoordinates());

        return triangleOperations.calculatePerimeter(triangle);
    }

    private WeatherTypeEnum predictWeather(final @NotNull Integer day) {
        PreconditionsHelper.checkNotNullAndArgument(day, "Day", day >= 0, GREATER_THAN_ZERO);

        planetsCoordinates.calculatePlanetsCoordinates(day);

        final WeatherTypeEnum weatherTypeEnum = checkWeather();

        logger.info("Predicted weather forecast at day {} => {}", day, weatherTypeEnum.name());

        return weatherTypeEnum;
    }

    private WeatherTypeEnum checkWeather() {
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
