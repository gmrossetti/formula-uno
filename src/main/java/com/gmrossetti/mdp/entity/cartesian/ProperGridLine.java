package com.gmrossetti.mdp.entity.cartesian;

import java.util.List;
import java.util.Set;

public interface ProperGridLine {
    double getSlopeCoefficient();
    double getSlopeCoefficientToDegrees();
    Set<Point> getLineIntersectionsWithGrid();
    List<GridPoint> getNearestGridPointsOnIntersections();
}
