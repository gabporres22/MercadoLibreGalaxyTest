package com.gporres.mercadolibre.galaxytest.rest.dto;

import java.io.Serializable;

public class Summary implements Serializable {
    private long rainPeriods;
    private long dryPeriods;
    private long optimalPeriods;
    private long unknowDays;
    private Integer maximumRainingDay;

    public long getRainPeriods() {
        return rainPeriods;
    }

    public void setRainPeriods(long rainPeriods) {
        this.rainPeriods = rainPeriods;
    }

    public long getDryPeriods() {
        return dryPeriods;
    }

    public void setDryPeriods(long dryPeriods) {
        this.dryPeriods = dryPeriods;
    }

    public long getOptimalPeriods() {
        return optimalPeriods;
    }

    public void setOptimalPeriods(long optimalPeriods) {
        this.optimalPeriods = optimalPeriods;
    }

    public Integer getMaximumRainingDay() {
        return maximumRainingDay;
    }

    public void setMaximumRainingDay(Integer maximumRainingDay) {
        this.maximumRainingDay = maximumRainingDay;
    }

    public long getUnknowDays() {
        return unknowDays;
    }

    public void setUnknowDays(long unknowDays) {
        this.unknowDays = unknowDays;
    }
}
