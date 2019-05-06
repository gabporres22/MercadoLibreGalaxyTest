package com.gporres.mercadolibre.galaxytest.model;

import com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Galaxy {
    private static final Logger logger = LoggerFactory.getLogger(Galaxy.class);

    private Coordinates sunCoordinates = new Coordinates(Double.valueOf(0), Double.valueOf(0));

    private Planet ferengi;
    private Planet vulcano;
    private Planet betasoide;

    public Galaxy(final Planet ferengi, final Planet vulcano, final Planet betasoide) {
        PreconditionsHelper.checkNotNull(ferengi);
        PreconditionsHelper.checkNotNull(vulcano);
        PreconditionsHelper.checkNotNull(betasoide);

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
