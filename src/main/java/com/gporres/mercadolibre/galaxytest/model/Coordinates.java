package com.gporres.mercadolibre.galaxytest.model;

public class Coordinates {
    private Double x;
    private Double y;

    public Coordinates(final Double x, final Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(x =" + x + ", y =" + y + ")";
    }
}
