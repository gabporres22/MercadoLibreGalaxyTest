package com.gporres.mercadolibre.galaxytest.operations;

import com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper;
import com.gporres.mercadolibre.galaxytest.model.Coordinates;
import com.gporres.mercadolibre.galaxytest.model.Planet;
import com.gporres.mercadolibre.galaxytest.model.enums.OrbitModeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

import static com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper.GREATER_THAN_ZERO;

@Component
public class PlanetOperations {
    private static final Logger logger = LoggerFactory.getLogger(PlanetOperations.class);
    private static final Integer ORBIT_DEGREES = 360;

    public Integer calculateDegreesFromXAxis(final @NotNull Planet planet, final @NotNull Integer day) {
        PreconditionsHelper.checkNotNull(planet, "Planet");
        PreconditionsHelper.checkNotNullAndArgument(day, "Day", day >= 0, GREATER_THAN_ZERO);

        logger.debug("Calculate {} Degrees from X at day {}", planet.getName(), day);

        Integer currentDegrees = (planet.getDailyAdvanceDegrees() * day) % ORBIT_DEGREES;

        if (OrbitModeEnum.CLOCKWISE.equals(planet.getOrbitMode()) && currentDegrees != 0) {
            currentDegrees = ORBIT_DEGREES - currentDegrees;
        }

        return currentDegrees;
    }

    public Coordinates calculateCoordinates(final @NotNull  Planet planet, final @NotNull Integer day) {
        PreconditionsHelper.checkNotNull(planet, "Planet");
        PreconditionsHelper.checkNotNullAndArgument(day, "Day",day >= 0, GREATER_THAN_ZERO);

        logger.debug("Calculate {} Coordinates at day {}", planet.getName(), day);

        final Integer degrees = calculateDegreesFromXAxis(planet, day);
        final Double radians = Math.toRadians(degrees);

        final Double x = planet.getDistanceToTheSun() * Math.cos(radians);
        final Double y = planet.getDistanceToTheSun() * Math.sin(radians);

        return new Coordinates(x,y);
    }
}
