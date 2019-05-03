package com.gporres.mercadolibre.galaxytest.model;

import com.gporres.mercadolibre.galaxytest.model.enums.OrbitModeEnum;

public class Planet {
    private String name;
    private Integer distanceToTheSun;
    private Integer diaryDegrees;
    private OrbitModeEnum orbitMode;

    public Planet(final String name, final Integer distanceToTheSun, final Integer diaryDegrees, final OrbitModeEnum orbitMode) {
        this.name = name;
        this.distanceToTheSun = distanceToTheSun;
        this.diaryDegrees = diaryDegrees;
        this.orbitMode = orbitMode;
    }

    public String getName() {
        return name;
    }

    public Integer getDistanceToTheSun() {
        return distanceToTheSun;
    }

    public Integer getDiaryDegrees() {
        return diaryDegrees;
    }

    public OrbitModeEnum getOrbitMode() {
        return orbitMode;
    }
}
