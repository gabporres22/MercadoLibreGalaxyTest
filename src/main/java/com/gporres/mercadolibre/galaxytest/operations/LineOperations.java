package com.gporres.mercadolibre.galaxytest.operations;

import com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper;
import com.gporres.mercadolibre.galaxytest.model.Coordinates;
import com.gporres.mercadolibre.galaxytest.model.Galaxy;
import com.gporres.mercadolibre.galaxytest.operations.geometric.Line;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Component
public class LineOperations {
    private static final Logger logger = LoggerFactory.getLogger(LineOperations.class);
    private static double PRECISION = 0.01;

    public Boolean isPointInLine(final @NotNull Line line, final @NotNull Coordinates point) {
        PreconditionsHelper.checkNotNull(line, "Line");
        PreconditionsHelper.checkNotNull(point, "Point");

        logger.debug("Check if point is in line.");

        return calculateSlope(line).map(m -> {
            final Double calculatedY = m * point.getX() + getYAxisIntersection(line, m);
            return Math.abs(calculatedY - point.getY()) < PRECISION;
        }).orElse(
                Math.abs(line.getPointA().getX() - point.getX()) < PRECISION
        );
    }

    public Double calculateDistance(final @NotNull Line line) {
        PreconditionsHelper.checkNotNull(line, "Line");

        logger.debug("Calculate Distance");
        final Double yPowDistance = Math.pow(line.getPointB().getY() - line.getPointA().getY(), 2);
        final Double xPowDistance = Math.pow(line.getPointB().getX() - line.getPointA().getX(), 2);

        return Math.sqrt(yPowDistance + xPowDistance);
    }

    private Optional<Double> calculateSlope(final @NotNull Line line) {
        PreconditionsHelper.checkNotNull(line, "Line");

        logger.debug("Calculate Slope");

        final Double deltaX = line.getPointB().getX() - line.getPointA().getX();

        if (deltaX == 0d)
            return Optional.empty(); // It's a vertical line

        final Double deltaY = line.getPointB().getY() - line.getPointA().getY();
        return Optional.of(deltaY / deltaX);
    }

    private Double getYAxisIntersection(final @NotNull Line line, final @NotNull Double slope) {
        PreconditionsHelper.checkNotNull(line, "Line");
        PreconditionsHelper.checkNotNull(slope, "Slope");

        return line.getPointA().getY() - slope * line.getPointA().getX();
    }
}
