package com.gporres.mercadolibre.galaxytest.model;

import com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper;
import javax.validation.constraints.NotNull;

public class Coordinates {
    private Double x;
    private Double y;

    public Coordinates() {
    }

    public Coordinates(final @NotNull Double x, final @NotNull Double y) {
        PreconditionsHelper.checkNotNull(x, "X");
        PreconditionsHelper.checkNotNull(y, "Y");

        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
