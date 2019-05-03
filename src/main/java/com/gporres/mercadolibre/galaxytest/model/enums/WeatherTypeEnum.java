package com.gporres.mercadolibre.galaxytest.model.enums;

public enum WeatherTypeEnum {
    DRY("Sequía"),
    WET("Lluvia"),
    OPTIMAL_CONDITIONS("Presión y temperatura óptimos"),
    UNKNOW("Desconocido");

    private final String label;

    WeatherTypeEnum(final String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public String getLabel() {
        return label;
    }
}
