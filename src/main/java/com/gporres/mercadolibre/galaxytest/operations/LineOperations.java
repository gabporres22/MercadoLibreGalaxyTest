package com.gporres.mercadolibre.galaxytest.operations;

import com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper;
import com.gporres.mercadolibre.galaxytest.model.Coordinates;
import com.gporres.mercadolibre.galaxytest.operations.geometric.Line;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Component
public class LineOperations {
    private static double PRECISION = 0.01;

    private Optional<Double> calculateSlope(final @NotNull Line line) {
        PreconditionsHelper.checkNotNull(line);

        final Double deltaX = line.getPointB().getX() - line.getPointA().getX();

        if (deltaX == 0d) {
            return Optional.empty(); // It's a vertical line
        }

        final Double deltaY = line.getPointB().getY() - line.getPointA().getY();
        return Optional.of(deltaY / deltaX);
    }

    private Double getYAxisIntersection(final @NotNull Line line, final @NotNull Double slope) {
        PreconditionsHelper.checkNotNull(line);
        PreconditionsHelper.checkNotNull(slope);

        return line.getPointA().getY() - slope * line.getPointA().getX();
    }

    public boolean isPointInLine(final @NotNull Line line, final @NotNull Coordinates point) {
        PreconditionsHelper.checkNotNull(line);
        PreconditionsHelper.checkNotNull(point);

        return calculateSlope(line).map(m -> {
            final Double calculatedY = m * point.getX() + getYAxisIntersection(line, m);
            return Math.abs(calculatedY - point.getY()) < PRECISION;
        }).orElse(
                Math.abs(line.getPointA().getX() - point.getX()) < PRECISION
        );
    }

    public Double getDistance(final @NotNull Line line) {
        final Double yPowDistance = Math.pow(line.getPointB().getY() - line.getPointA().getY(), 2);
        final Double xPowDistance = Math.pow(line.getPointB().getX() - line.getPointA().getX(), 2);

        return Math.sqrt(yPowDistance + xPowDistance);
    }
}
