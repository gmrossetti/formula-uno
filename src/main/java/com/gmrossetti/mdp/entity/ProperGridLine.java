package com.gmrossetti.mdp.entity;

import java.util.Set;

public interface ProperGridLine {
    double getSlopeCoefficient();
    Set<Point> getLineIntersectionsWithGrid();
    Set<GridPoint> getNearestGridPointsOnIntersections();
}
