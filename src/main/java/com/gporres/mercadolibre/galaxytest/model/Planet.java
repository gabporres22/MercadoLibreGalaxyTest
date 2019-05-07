package com.gporres.mercadolibre.galaxytest.model;

import com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper;
import com.gporres.mercadolibre.galaxytest.model.enums.OrbitModeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper.GREATER_THAN_ZERO;

public class Planet {
    private static final Logger logger = LoggerFactory.getLogger(Planet.class);

    private String name;
    private Integer distanceToTheSun;
    private Integer dailyAdvanceDegrees;
    private OrbitModeEnum orbitMode;

    public Planet(final String name, final Integer distanceToTheSun, final Integer dailyAdvanceDegrees, final OrbitModeEnum orbitMode) {
        PreconditionsHelper.checkNotNull(name, "Name");
        PreconditionsHelper.checkNotNullAndArgument(distanceToTheSun, "DistanceToTheSun",distanceToTheSun >= 0, GREATER_THAN_ZERO);
        PreconditionsHelper.checkNotNullAndArgument(dailyAdvanceDegrees, "DailyAdvanceDegrees", dailyAdvanceDegrees >= 0, GREATER_THAN_ZERO);
        PreconditionsHelper.checkNotNull(orbitMode, "OrbitMode");

        this.name = name;
        this.distanceToTheSun = distanceToTheSun;
        this.dailyAdvanceDegrees = dailyAdvanceDegrees;
        this.orbitMode = orbitMode;

        logger.debug("Planet {} created.", this.name);
    }

    public String getName() {
        return name;
    }

    public Integer getDistanceToTheSun() {
        return distanceToTheSun;
    }

    public Integer getDailyAdvanceDegrees() {
        return dailyAdvanceDegrees;
    }

    public OrbitModeEnum getOrbitMode() {
        return orbitMode;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", distanceToTheSun=" + distanceToTheSun +
                ", dailyAdvanceDegrees=" + dailyAdvanceDegrees +
                ", orbitMode=" + orbitMode +
                '}';
    }
}
