package com.gporres.mercadolibre.galaxytest.test.operations;

import com.gporres.mercadolibre.galaxytest.application.GalaxyTestApplication;
import com.gporres.mercadolibre.galaxytest.model.Galaxy;
import com.gporres.mercadolibre.galaxytest.operations.PlanetsCoordinates;
import com.gporres.mercadolibre.galaxytest.operations.TriangleOperations;
import com.gporres.mercadolibre.galaxytest.operations.geometric.Triangle;
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
public class TriangleOperationsTest {
    @Autowired
    private TriangleOperations triangleOperations;

    @Autowired
    private PlanetsCoordinates planetsCoordinates;

    @Autowired
    private Galaxy galaxy;

    private static Double MAX_TRIANGLE_PERIMETER = 6262.30;

    @Test(expected = NullPointerException.class)
    public void calculatePerimeterFailTriangleIsNull() {
        triangleOperations.calculatePerimeter(null);
    }

    @Test
    public void calculatePerimeterOk() {
        planetsCoordinates.calculatePlanetsCoordinates(108);
        final Triangle triangle = new Triangle(planetsCoordinates.getFerengiCoordinates(), planetsCoordinates.getVulcanoCoordinates(), planetsCoordinates.getBetasoideCoordinates());
        final Double perimeter = triangleOperations.calculatePerimeter(triangle);
        final DecimalFormat decimalFormat = new DecimalFormat("#.##");

        assertEquals(Double.valueOf(decimalFormat.format(perimeter)), MAX_TRIANGLE_PERIMETER);
    }

    @Test(expected = NullPointerException.class)
    public void isPointInsideFailTriangleIsNull() {
        planetsCoordinates.calculatePlanetsCoordinates(0);
        triangleOperations.isPointInside(null, planetsCoordinates.getVulcanoCoordinates());
    }

    @Test(expected = NullPointerException.class)
    public void isPointInsideFailCoordinatesIsNull() {
        planetsCoordinates.calculatePlanetsCoordinates(0);
        final Triangle triangle = new Triangle(planetsCoordinates.getFerengiCoordinates(), planetsCoordinates.getVulcanoCoordinates(), planetsCoordinates.getBetasoideCoordinates());
        triangleOperations.isPointInside(triangle, null);
    }

    @Test
    public void isPointInsideFailCoordinatesOk() {
        planetsCoordinates.calculatePlanetsCoordinates(108);
        final Triangle triangle = new Triangle(planetsCoordinates.getFerengiCoordinates(), planetsCoordinates.getVulcanoCoordinates(), planetsCoordinates.getBetasoideCoordinates());

        assertTrue(triangleOperations.isPointInside(triangle, galaxy.getSunCoordinates()));
    }
}
