package com.gporres.mercadolibre.galaxytest.model;

import com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper;
import com.gporres.mercadolibre.galaxytest.model.enums.OrbitModeEnum;

import static com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper.GREATER_THAN_ZERO;

public class Planet {
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
}
