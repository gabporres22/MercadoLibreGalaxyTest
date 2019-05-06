package com.gporres.mercadolibre.galaxytest.scheduler;

import com.gporres.mercadolibre.galaxytest.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static java.lang.Long.MAX_VALUE;

@Component
public class WeatherForecastScheduledJob {
    private static final Logger logger = LoggerFactory.getLogger(WeatherForecastScheduledJob.class);

    private static final Integer DAYS_PER_YEAR = 360;
    private static final Integer YEARS_TO_PREDICT = 10;

    @Autowired
    private WeatherForecastService weatherForecastService;

    private Boolean isJobAlreadyRunned = false;

    @Scheduled(fixedDelay = 1000 * 30, initialDelay = MAX_VALUE)
    public void predictWeather() {
        logger.info("Starting WeatherForecastScheduledJob");

        if(!isJobAlreadyRunned) {
            weatherForecastService.predictAndStoreWeatherForecast(0, DAYS_PER_YEAR * YEARS_TO_PREDICT);
            isJobAlreadyRunned = true;
        }

        logger.info("WeatherForecastScheduledJob finished succesfully");
    }
}
