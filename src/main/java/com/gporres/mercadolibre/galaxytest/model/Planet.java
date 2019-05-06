package com.gporres.mercadolibre.galaxytest.model;

import com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper;
import com.gporres.mercadolibre.galaxytest.model.enums.OrbitModeEnum;

public class Planet {
    private String name;
    private Integer distanceToTheSun;
    private Integer dailyAdvanceDegrees;
    private OrbitModeEnum orbitMode;

    public Planet(final String name, final Integer distanceToTheSun, final Integer dailyAdvanceDegrees, final OrbitModeEnum orbitMode) {
        PreconditionsHelper.checkNotNull(name);
        PreconditionsHelper.checkNotNullAndArgument(distanceToTheSun, distanceToTheSun > 0);
        PreconditionsHelper.checkNotNullAndArgument(dailyAdvanceDegrees, dailyAdvanceDegrees > 0);
        PreconditionsHelper.checkNotNull(orbitMode);

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
