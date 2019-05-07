package com.gporres.mercadolibre.galaxytest.test.operations;

import com.gporres.mercadolibre.galaxytest.application.GalaxyTestApplication;
import com.gporres.mercadolibre.galaxytest.operations.PlanetsCoordinates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GalaxyTestApplication.class})
public class PlanetCoordinatesTest {
    @Autowired
    private PlanetsCoordinates planetsCoordinates;

    @Test(expected = IllegalArgumentException.class)
    public void calculatePlanetsCoordinatesFailOutOfRange() {
        planetsCoordinates.calculatePlanetsCoordinates(-1);
    }

    @Test
    public void calculatePlanetsCoordinatesOk() {
        planetsCoordinates.calculatePlanetsCoordinates(108);

        assertNotNull(planetsCoordinates.getBetasoideCoordinates());
        assertNotNull(planetsCoordinates.getFerengiCoordinates());
        assertNotNull(planetsCoordinates.getVulcanoCoordinates());
    }
}
