package com.gporres.mercadolibre.galaxytest.model.builder;

import com.gporres.mercadolibre.galaxytest.model.Planet;
import com.gporres.mercadolibre.galaxytest.model.enums.OrbitModeEnum;

import java.util.function.Consumer;

public class PlanetBuilder {
    public String name;
    public Integer distanceToTheSun;
    public Integer dailyAdvanceDegrees;
    public OrbitModeEnum orbitMode;

    public PlanetBuilder with(final Consumer<PlanetBuilder> builderFunction) {
        builderFunction.accept(this);
        return this;
    }

    public Planet createPlanet() {
        return new Planet(name, distanceToTheSun, dailyAdvanceDegrees, orbitMode);
    }
}
