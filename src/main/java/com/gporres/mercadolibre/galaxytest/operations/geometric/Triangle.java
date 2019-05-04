package com.gporres.mercadolibre.galaxytest.operations.geometric;

import com.gporres.mercadolibre.galaxytest.model.Coordinates;

public class Triangle {
    private Coordinates pointA;
    private Coordinates pointB;
    private Coordinates pointC;

    public Triangle(final Coordinates pointA, final Coordinates pointB, final Coordinates pointC) {
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
