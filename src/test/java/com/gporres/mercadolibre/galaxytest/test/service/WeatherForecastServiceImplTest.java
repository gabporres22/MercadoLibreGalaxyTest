package com.gporres.mercadolibre.galaxytest.test.service;

import com.gporres.mercadolibre.galaxytest.application.GalaxyTestApplication;
import com.gporres.mercadolibre.galaxytest.model.entities.WeatherForecast;
import com.gporres.mercadolibre.galaxytest.model.enums.WeatherTypeEnum;
import com.gporres.mercadolibre.galaxytest.rest.dto.Summary;
import com.gporres.mercadolibre.galaxytest.scheduler.WeatherForecastScheduledJob;
import com.gporres.mercadolibre.galaxytest.service.WeatherForecastService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.gporres.mercadolibre.galaxytest.model.enums.WeatherTypeEnum.WET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GalaxyTestApplication.class})
public class WeatherForecastServiceImplTest {
    private static int MAX_RAINING_DAY = 108;

    @Autowired
    private WeatherForecastService weatherForecastService;

    @Autowired
    private WeatherForecastScheduledJob weatherForecastScheduledJob;

    @Test(expected = IllegalArgumentException.class)
    public void findByDayFailOutOfRange(){
        weatherForecastService.findByDay(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void predictAndStoreWeatherForecastTestFailDayFromOutOfRange() {
        weatherForecastService.predictAndStoreWeatherForecast(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void predictAndStoreWeatherForecastTestFailDayToOutOfRange() {
        weatherForecastService.predictAndStoreWeatherForecast(0, 0);
    }

    @Test
    public void findDayTestOk() {
        weatherForecastScheduledJob.predictWeather();

        final Optional<WeatherForecast> weatherForecast = weatherForecastService.findByDay(MAX_RAINING_DAY);

        assertEquals(weatherForecast.isPresent(), true);
        assertEquals(weatherForecast.get().getWeather(), WET);
    }

    @Test
    public void calculateSummaryOk() {
        final Summary summary = weatherForecastService.calculateWeatherSummary();

        assertNotNull(summary);
        assertEquals(summary.getMaximumRainingDay().intValue(), MAX_RAINING_DAY);
    }
}
