package com.gporres.mercadolibre.galaxytest.operations.geometric;

import com.gporres.mercadolibre.galaxytest.model.Coordinates;

public class Line {
    private Coordinates pointA;
    private Coordinates pointB;

    public Line(final Coordinates pointA, final Coordinates pointB) {
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
