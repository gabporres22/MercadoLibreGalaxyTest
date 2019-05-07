package com.gporres.mercadolibre.galaxytest.test.operations;

import com.gporres.mercadolibre.galaxytest.application.GalaxyTestApplication;
import com.gporres.mercadolibre.galaxytest.model.Coordinates;
import com.gporres.mercadolibre.galaxytest.operations.LineOperations;
import com.gporres.mercadolibre.galaxytest.operations.PlanetsCoordinates;
import com.gporres.mercadolibre.galaxytest.operations.geometric.Line;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GalaxyTestApplication.class})
public class LineOperationsTest {
    @Autowired
    private LineOperations lineOperations;

    @Autowired
    private PlanetsCoordinates planetsCoordinates;

    private static Double LINE_FERENGI_BETASOIDE_DAY_108 = 970.04;

    @Test(expected = NullPointerException.class)
    public void isPointInLineFailLineNull() {
        lineOperations.isPointInLine(null, new Coordinates(1d, 1d));
    }

    @Test(expected = NullPointerException.class)
    public void isPointInLineFailCoordinatesNull() {
        lineOperations.isPointInLine(new Line(new Coordinates(1d, 1d), new Coordinates(1d, 1d)), null);
    }

    @Test
    public void isPointInLineOk() {
        planetsCoordinates.calculatePlanetsCoordinates(0);
        final Line ferengiBetasoideline = new Line(planetsCoordinates.getFerengiCoordinates(), planetsCoordinates.getBetasoideCoordinates());

        assertTrue(lineOperations.isPointInLine(ferengiBetasoideline, planetsCoordinates.getVulcanoCoordinates()));
    }

    @Test(expected = NullPointerException.class)
    public void calculateDistanceFailLineNull() {
        lineOperations.calculateDistance(null);
    }

    @Test
    public void calculateDistanceOk() {
        planetsCoordinates.calculatePlanetsCoordinates(108);
        final Line ferengiBetasoideline = new Line(planetsCoordinates.getFerengiCoordinates(), planetsCoordinates.getBetasoideCoordinates());
        final DecimalFormat decimalFormat = new DecimalFormat("#.##");
        final Double distance = lineOperations.calculateDistance(ferengiBetasoideline);

        assertEquals(Double.valueOf(decimalFormat.format(distance)), LINE_FERENGI_BETASOIDE_DAY_108);
    }
}
