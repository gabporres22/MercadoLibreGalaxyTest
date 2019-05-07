package com.gporres.mercadolibre.galaxytest.operations;

import com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper;
import com.gporres.mercadolibre.galaxytest.model.Coordinates;
import com.gporres.mercadolibre.galaxytest.model.Galaxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

import static com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper.GREATER_THAN_ZERO;

@Component
public class PlanetsCoordinates {
    private static final Logger logger = LoggerFactory.getLogger(PlanetsCoordinates.class);

    @Autowired
    private Galaxy galaxy;

    @Autowired
    private PlanetOperations planetOperations;

    private Coordinates ferengiCoordinates;
    private Coordinates vulcanoCoordinates;
    private Coordinates betasoideCoordinates;

    public void calculatePlanetsCoordinates(final @NotNull Integer day) {
        PreconditionsHelper.checkNotNullAndArgument(day, "Day", day >= 0, GREATER_THAN_ZERO);

        logger.debug("Calculate planets coordinates at day {}", day);

        ferengiCoordinates = planetOperations.calculateCoordinates(galaxy.getFerengi(), day);
        vulcanoCoordinates = planetOperations.calculateCoordinates(galaxy.getVulcano(), day);
        betasoideCoordinates = planetOperations.calculateCoordinates(galaxy.getBetasoide(), day);
    }

    public Coordinates getFerengiCoordinates() {
        return ferengiCoordinates;
    }

    public Coordinates getVulcanoCoordinates() {
        return vulcanoCoordinates;
    }

    public Coordinates getBetasoideCoordinates() {
        return betasoideCoordinates;
    }
}
