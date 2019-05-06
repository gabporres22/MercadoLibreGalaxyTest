package com.gporres.mercadolibre.galaxytest.operations.geometric;

import com.gporres.mercadolibre.galaxytest.model.Coordinates;

import javax.validation.constraints.NotNull;

public class Triangle {
    private Coordinates pointA;
    private Coordinates pointB;
    private Coordinates pointC;

    public Triangle(final @NotNull Coordinates pointA, final @NotNull Coordinates pointB, final @NotNull Coordinates pointC) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
    }

    public Coordinates getPointA() {
        return pointA;
    }

    public Coordinates getPointB() {
        return pointB;
    }

    public Coordinates getPointC() {
        return pointC;
    }
}
