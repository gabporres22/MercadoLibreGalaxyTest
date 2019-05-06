package com.gporres.mercadolibre.galaxytest.application;

import com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper;
import com.gporres.mercadolibre.galaxytest.model.Galaxy;
import com.gporres.mercadolibre.galaxytest.model.Planet;
import com.gporres.mercadolibre.galaxytest.model.builder.GalaxyBuilder;
import com.gporres.mercadolibre.galaxytest.operations.*;
import com.gporres.mercadolibre.galaxytest.service.WeatherForecastService;
import com.gporres.mercadolibre.galaxytest.service.impl.WeatherForecastServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.gporres.mercadolibre.galaxytest")
@EnableJpaRepositories("com.gporres.mercadolibre.galaxytest.repository")
@EntityScan("com.gporres.mercadolibre.galaxytest.model.entities")
@EnableScheduling
public class GalaxyTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(GalaxyTestApplication.class, args);
    }

    @Bean
    Galaxy galaxy(){
        return GalaxyBuilder.buildGalaxy();
    }

    @Bean
    WeatherForecastService weatherForecastService(){
        return new WeatherForecastServiceImpl();
    }

    @Bean
    GalaxyOperations galaxyOperations() {
        return new GalaxyOperations();
    }

    @Bean
    PlanetOperations planetOperations() {
        return new PlanetOperations();
    }

    @Bean
    PlanetsCoordinates planetsCoordinates() {
        return new PlanetsCoordinates();
    }

    @Bean
    LineOperations lineOperations() {
        return new LineOperations();
    }

    @Bean
    TriangleOperations triangleOperations() {
        return new TriangleOperations();
    }
}
