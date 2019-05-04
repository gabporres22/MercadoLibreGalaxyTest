package com.gporres.mercadolibre.galaxytest.model.builder;

import com.gporres.mercadolibre.galaxytest.model.Galaxy;
import com.gporres.mercadolibre.galaxytest.model.Planet;

import static com.gporres.mercadolibre.galaxytest.model.enums.OrbitModeEnum.CLOCKWISE;
import static com.gporres.mercadolibre.galaxytest.model.enums.OrbitModeEnum.COUNTER_CLOCKWISE;

public class GalaxyBuilder {
    public static Galaxy buildGalaxy() {

        Planet ferengi = new PlanetBuilder().with(builder -> {
            builder.name = "Ferengi";
            builder.distanceToTheSun = 500;
            builder.dailyAdvanceDegrees = 1;
            builder.orbitMode = CLOCKWISE;
        }).createPlanet();

        Planet betasoide = new PlanetBuilder().with(builder -> {
            builder.name = "Betasoide";
            builder.distanceToTheSun = 2000;
            builder.dailyAdvanceDegrees = 3;
            builder.orbitMode = CLOCKWISE;
        }).createPlanet();


        Planet vulcano = new PlanetBuilder().with(builder -> {
            builder.name = "Vulcano";
            builder.distanceToTheSun = 1000;
            builder.dailyAdvanceDegrees = 5;
            builder.orbitMode = COUNTER_CLOCKWISE;
        }).createPlanet();

        return new Galaxy(ferengi, betasoide, vulcano);
    }
}
