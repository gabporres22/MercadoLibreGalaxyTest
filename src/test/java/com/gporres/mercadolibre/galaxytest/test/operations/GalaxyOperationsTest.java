package com.gporres.mercadolibre.galaxytest.test.operations;

import com.gporres.mercadolibre.galaxytest.application.GalaxyTestApplication;
import com.gporres.mercadolibre.galaxytest.model.enums.WeatherTypeEnum;
import com.gporres.mercadolibre.galaxytest.operations.GalaxyOperations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GalaxyTestApplication.class})
public class GalaxyOperationsTest {
    @Autowired
    private GalaxyOperations galaxyOperations;

    private static Double MAX_TRIANGLE_PERIMETER = 6262.30;

    @Test(expected = IllegalArgumentException.class)
    public void predictWeatherFailDayFromOutOfRange() {
        galaxyOperations.predictWeather(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void predictWeatherFailDayToOutOfRange() {
        galaxyOperations.predictWeather(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateTrianglePerimeterFailDayOutOfRange() {
        galaxyOperations.calculatePlanetsTrianglePerimeter(-1);
    }

    @Test
    public void predictWeatherOk() {
        final Map<Integer, WeatherTypeEnum> weather = galaxyOperations.predictWeather(108, 109);
        final Optional<WeatherTypeEnum> weatherForecast = weather.values().stream().findFirst();

        assertEquals(weatherForecast.isPresent(), true);
        assertEquals(weatherForecast.get(), WeatherTypeEnum.WET);
    }

    @Test
    public void calculateTrianglePerimeterOk() {
        final Double trianglePerimeter = galaxyOperations.calculatePlanetsTrianglePerimeter(108);
        final DecimalFormat decimalFormat = new DecimalFormat("#.##");

        assertEquals(Double.valueOf(decimalFormat.format(trianglePerimeter)), MAX_TRIANGLE_PERIMETER);
    }
}
