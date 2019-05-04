package com.gporres.mercadolibre.galaxytest.operations;

import com.gporres.mercadolibre.galaxytest.model.Coordinates;
import com.gporres.mercadolibre.galaxytest.operations.geometric.Line;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LineOperations {
    private static double PRECISION = 0.01;

    private Optional<Double> calcuateSlope(final Line line) {
        double deltaX = line.getPointB().getX() - line.getPointA().getX();

        if (deltaX == 0d) {
            return Optional.empty(); // It's a vertical line
        }

        double deltaY = line.getPointB().getY() - line.getPointA().getY();
        return Optional.of(deltaY / deltaX);
    }

    private Double getYAxisIntersection(final Line line, final Double slope) {
        return line.getPointA().getY() - slope * line.getPointA().getX();
    }

    public boolean isPointInLine(final Line line, final Coordinates point) {
        return calcuateSlope(line).map(m -> {
            Double calculatedY = m * point.getX() + getYAxisIntersection(line, m);
            return Math.abs(calculatedY - point.getY()) < PRECISION;
        }).orElse(
                Math.abs(line.getPointA().getX() - point.getX()) < PRECISION
        );
    }

    public Double getDistance(final Line line) {
        Double yPowDistance = Math.pow(line.getPointB().getY() - line.getPointA().getY(), 2);
        Double xPowDistance = Math.pow(line.getPointB().getX() - line.getPointA().getX(), 2);

        return Math.sqrt(yPowDistance + xPowDistance);
    }
}
