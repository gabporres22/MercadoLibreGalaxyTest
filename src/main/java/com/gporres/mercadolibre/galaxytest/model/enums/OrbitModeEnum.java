package com.gporres.mercadolibre.galaxytest.model.enums;

public enum OrbitModeEnum {
    CLOCKWISE(-1),
    COUNTER_CLOCKWISE(1);

    private final Integer value;

    OrbitModeEnum(final Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
