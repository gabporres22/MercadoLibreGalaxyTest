package com.gporres.mercadolibre.galaxytest.test.operations;

import com.gporres.mercadolibre.galaxytest.application.GalaxyTestApplication;
import com.gporres.mercadolibre.galaxytest.model.Coordinates;
import com.gporres.mercadolibre.galaxytest.model.Galaxy;
import com.gporres.mercadolibre.galaxytest.operations.PlanetOperations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GalaxyTestApplication.class})
public class PlanetOperationsTest {
    @Autowired
    private PlanetOperations planetOperations;

    @Autowired
    private Galaxy galaxy;

    private static int VULCANO_DEGREES_108_DAY = 36;
    private static double VULCANO_AXIS_X_108_DAY = 1618.03;
    private static double VULCANO_AXIS_Y_108_DAY = 1175.57;

    @Test(expected = IllegalArgumentException.class)
    public void calculateDegreesFromXAxisFailDayOutOfRange() {
        planetOperations.calculateDegreesFromXAxis(galaxy.getVulcano(), -1);
    }

    @Test(expected = NullPointerException.class)
    public void calculateDegreesFromXAxisFailPlanetNull() {
        planetOperations.calculateDegreesFromXAxis(null, 1);
    }

    @Test
    public void calculateDegreesFromXAxisOk() {
        final int degrees = planetOperations.calculateDegreesFromXAxis(galaxy.getVulcano(), 108);
        assertEquals(degrees, VULCANO_DEGREES_108_DAY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateCoordinatesFailDayOutOfRange() {
        planetOperations.calculateCoordinates(galaxy.getVulcano(), -1);
    }

    @Test(expected = NullPointerException.class)
    public void calculateCoordinatesFailPlanetNull() {
        planetOperations.calculateCoordinates(null, 1);
    }

    @Test
    public void calculateCoordinatesOk() {
        final Coordinates coordinates = planetOperations.calculateCoordinates(galaxy.getVulcano(), 108);
        final DecimalFormat decimalFormat = new DecimalFormat("#.##");
        coordinates.setX(Double.valueOf(decimalFormat.format(coordinates.getX())));
        coordinates.setY(Double.valueOf(decimalFormat.format(coordinates.getY())));

        assertEquals(coordinates.getX().doubleValue(), VULCANO_AXIS_X_108_DAY, 0);
        assertEquals(coordinates.getY().doubleValue(), VULCANO_AXIS_Y_108_DAY, 0);
    }
}
