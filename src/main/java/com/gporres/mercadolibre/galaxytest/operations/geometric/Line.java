package com.gporres.mercadolibre.galaxytest.operations.geometric;

import com.gporres.mercadolibre.galaxytest.model.Coordinates;

import javax.validation.constraints.NotNull;

public class Line {
    private Coordinates pointA;
    private Coordinates pointB;

    public Line(final @NotNull Coordinates pointA, final @NotNull Coordinates pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public Coordinates getPointA() {
        return pointA;
    }

    public Coordinates getPointB() {
        return pointB;
    }
}
