package com.gporres.mercadolibre.galaxytest.operations.geometric;

import com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper;
import com.gporres.mercadolibre.galaxytest.model.Coordinates;

import javax.validation.constraints.NotNull;

public class Line {
    private Coordinates pointA;
    private Coordinates pointB;

    public Line(final @NotNull Coordinates pointA, final @NotNull Coordinates pointB) {
        PreconditionsHelper.checkNotNull(pointA, "PointA");
        PreconditionsHelper.checkNotNull(pointB, "PointB");

        this.pointA = pointA;
        this.pointB = pointB;
    }

    public Coordinates getPointA() {
        return pointA;
    }

    public Coordinates getPointB() {
        return pointB;
    }

    @Override
    public String toString() {
        return "Line{" +
                "pointA=" + pointA +
                ", pointB=" + pointB +
                '}';
    }
}
