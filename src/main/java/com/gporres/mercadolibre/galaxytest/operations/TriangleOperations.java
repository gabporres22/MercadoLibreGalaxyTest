package com.gporres.mercadolibre.galaxytest.operations;

import com.gporres.mercadolibre.galaxytest.helper.PreconditionsHelper;
import com.gporres.mercadolibre.galaxytest.model.Coordinates;
import com.gporres.mercadolibre.galaxytest.operations.geometric.Line;
import com.gporres.mercadolibre.galaxytest.operations.geometric.Triangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TriangleOperations {
    @Autowired
    private LineOperations lineOperations;

    public Double calculatePerimeter(final Triangle triangle) {
        PreconditionsHelper.checkNotNull(triangle, "Triangle");

        final Double distanceAB = lineOperations.calculateDistance(new Line(triangle.getPointA(), triangle.getPointB()));
        final Double distanceBC = lineOperations.calculateDistance(new Line(triangle.getPointB(), triangle.getPointC()));
        final Double distanceCA = lineOperations.calculateDistance(new Line(triangle.getPointC(), triangle.getPointA()));

        return distanceAB + distanceBC + distanceCA;
    }

    public Boolean isPointInside(final Triangle triangle, final Coordinates point) {
        PreconditionsHelper.checkNotNull(triangle, "Triangle");
        PreconditionsHelper.checkNotNull(point, "Point");

        final Double originalArea = calculateArea(triangle.getPointA(), triangle.getPointB(), triangle.getPointC());

        final Double areaReplacingA = calculateArea(point, triangle.getPointB(), triangle.getPointC());
        final Double areaReplacingB = calculateArea(triangle.getPointA(), point, triangle.getPointC());
        final Double areaReplacingC = calculateArea(triangle.getPointA(), triangle.getPointB(), point);

        return originalArea.equals(areaReplacingA + areaReplacingB + areaReplacingC);
    }

    private Double calculateArea(final Coordinates pointA, final Coordinates pointB, final Coordinates pointC) {
        PreconditionsHelper.checkNotNull(pointA, "PointA");
        PreconditionsHelper.checkNotNull(pointB, "PointB");
        PreconditionsHelper.checkNotNull(pointC, "PointC");

        double tmp = pointA.getX() * (pointB.getY() - pointC.getY()) +
                pointB.getX() * (pointC.getY() - pointA.getY()) +
                pointC.getX() * (pointA.getY() - pointB.getY());
        return Math.abs(tmp / 2);
    }
}
