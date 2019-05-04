package com.gporres.mercadolibre.galaxytest.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Galaxy {
    private static final Logger logger = LoggerFactory.getLogger(Galaxy.class);

    private Coordinates sunCoordinates = new Coordinates(Double.valueOf(0), Double.valueOf(0));

    private Planet ferengi;
    private Planet vulcano;
    private Planet betasoide;

    public Galaxy(Planet ferengi, Planet vulcano, Planet betasoide) {
        this.ferengi = ferengi;
        this.vulcano = vulcano;
        this.betasoide = betasoide;
    }

    public Coordinates getSunCoordinates() {
        return sunCoordinates;
    }

    public Planet getFerengi() {
        return ferengi;
    }

    public Planet getVulcano() {
        return vulcano;
    }

    public Planet getBetasoide() {
        return betasoide;
    }
}
