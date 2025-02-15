package com.gmrossetti.mdp.entity;

import java.util.HashSet;
import java.util.Set;

public class GridLine implements ProperGridLine {
    private final GridPoint gp1;
    private final GridPoint gp2;

    public boolean isDegenerate() {
        return gp1.equals(gp2);
    }

    public GridLine(GridPoint gp1, GridPoint gp2) {
        if (gp1 == null || gp2 == null) {
            throw new IllegalArgumentException("Grid points cannot be null");
        }

        this.gp1 = gp1;
        this.gp2 = gp2;
    }

    @Override
    public double getSlopeCoefficient(){
        if (isDegenerate()) throw new UnsupportedOperationException("Unsupported on degenerated GridLines");

        double deltaY = this.gp1.y - this.gp2.y;
        double deltaX = this.gp1.x - this.gp2.x;

        return deltaY / deltaX;
    }

    // Metodo che calcola i punti di intersezione intermedi
    @Override
    public Set<Point> getLineIntersectionsWithGrid() {
        if (isDegenerate()) throw new UnsupportedOperationException("Unsupported on degenerated GridLines");

        final Set<Point> intersectionPoints = new HashSet<>();

        final double m = this.getSlopeCoefficient();
        final double b = gp1.y - m * gp1.x;

        final int MIN_X = Math.min(gp1.x, gp2.x);
        final int MAX_X = Math.max(gp1.x, gp2.x);

        final int MIN_Y = Math.min(gp1.y, gp2.y);
        final int MAX_Y = Math.max(gp1.y, gp2.y);

        if(m == 0){
            for (int xi = MIN_X; xi <= MAX_X; xi++) {
                intersectionPoints.add(new Point(xi, gp1.y));
            }
        } else if(m == Double.POSITIVE_INFINITY || m == Double.NEGATIVE_INFINITY){
            for (int yi = MIN_Y; yi <= MAX_Y; yi++) {
                intersectionPoints.add(new Point(gp1.x, yi));
            }
        } else {
            for (int xi = MIN_X; xi <= MAX_X; xi++) {
                double yi = m * xi + b;
                intersectionPoints.add(new Point(xi, yi));
            }

            for (int yi = MIN_Y; yi <= MAX_Y; yi++) {
                double xi = (yi - b) / m;
                intersectionPoints.add(new Point(xi, yi));
            }
        }

        return intersectionPoints;
    }

    @Override
    public Set<GridPoint> getNearestGridPointsOnIntersections(){
        if (isDegenerate()) throw new UnsupportedOperationException("Unsupported on degenerated GridLines");

        Set<Point> intersectionPoints = this.getLineIntersectionsWithGrid();

        Set<GridPoint> intersectionGridPoints = new HashSet<>();

        for (Point intersectionPoint:
                intersectionPoints) {
            intersectionGridPoints.addAll(intersectionPoint.getNearestDiscretePoints());
        }

        return intersectionGridPoints;
    }
}
