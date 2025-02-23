package com.gmrossetti.mdp.entity.cartesian;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GridLine implements ProperGridLine {
    private final GridPoint start;

    public GridPoint getStart() {
        return start;
    }

    public GridPoint getEnd() {
        return end;
    }

    private final GridPoint end;

    public boolean isDegenerate() {
        return start.equals(end);
    }

    public GridLine(GridPoint start, GridPoint end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Grid points cannot be null");
        }

        this.start = start;
        this.end = end;
    }

    public Point getMedianPoint(){
        final double medianX = (double) (this.start.x + this.end.x) / 2;
        final double medianY = (double) (this.start.y + this.end.y) / 2;

        return new Point(medianX,medianY);
    }

    @Override
    public double getSlopeCoefficientToDegrees() {
        final double m = this.getSlopeCoefficient();

        if (Double.isInfinite(m)) {
            return (m > 0) ? 90.0 : -90.0;
        }
        return Math.toDegrees(Math.atan(m));
    }

    @Override
    public double getSlopeCoefficient(){
        if (isDegenerate()) throw new UnsupportedOperationException("Unsupported on degenerated GridLines");

        double deltaY = this.start.y - this.end.y;
        double deltaX = this.start.x - this.end.x;

        return deltaY / deltaX;
    }

    // Metodo che calcola i punti di intersezione intermedi
    @Override
    public Set<Point> getLineIntersectionsWithGrid() {
        if (isDegenerate()) throw new UnsupportedOperationException("Unsupported on degenerated GridLines");

        final Set<Point> intersectionPoints = new HashSet<>();

        final double m = this.getSlopeCoefficient();
        final double b = start.y - m * start.x;

        final int MIN_X = Math.min(start.x, end.x);
        final int MAX_X = Math.max(start.x, end.x);

        final int MIN_Y = Math.min(start.y, end.y);
        final int MAX_Y = Math.max(start.y, end.y);

        if(m == 0){
            for (int xi = MIN_X; xi <= MAX_X; xi++) {
                intersectionPoints.add(new Point(xi, start.y));
            }
        } else if(m == Double.POSITIVE_INFINITY || m == Double.NEGATIVE_INFINITY){
            for (int yi = MIN_Y; yi <= MAX_Y; yi++) {
                intersectionPoints.add(new Point(start.x, yi));
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
    public List<GridPoint> getNearestGridPointsOnIntersections(){
        if (isDegenerate()) throw new UnsupportedOperationException("Unsupported on degenerated GridLines");

        Set<Point> intersectionPoints = this.getLineIntersectionsWithGrid();

        Set<GridPoint> intersectionGridPoints = new HashSet<>();

        for (Point intersectionPoint:
                intersectionPoints) {
            intersectionGridPoints.addAll(intersectionPoint.getNearestDiscretePoints());
        }

        List<GridPoint> sortedByDistanceGridPoints = intersectionGridPoints.stream()
                .sorted(Comparator.comparingDouble(p -> p.distanceTo(this.start)))
                .collect(Collectors.toList());

        return sortedByDistanceGridPoints;
    }

    @Override
    public String toString(){
        return "GridLine [start: " + this.start + ", end: " + this.end + "]";
    }
}
