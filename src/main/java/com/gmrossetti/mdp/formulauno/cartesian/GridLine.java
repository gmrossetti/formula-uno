package com.gmrossetti.mdp.formulauno.cartesian;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a line segment in a Cartesian grid defined by two grid points.
 * Provides methods to calculate properties such as slope, median point, and
 * intersections with the grid.
 */
public class GridLine {
    
    /** The starting point of the grid line. */
    private final GridPoint start;

    /**
     * Gets the starting point of the grid line.
     *
     * @return the starting point of the grid line
     */
    public GridPoint getStart() {
        return start;
    }

    /**
     * Gets the ending point of the grid line.
     *
     * @return the ending point of the grid line
     */
    public GridPoint getEnd() {
        return end;
    }

    /** The ending point of the grid line. */
    private final GridPoint end;

    /**
     * Checks if the grid line is degenerate (i.e., the start and end points are the same).
     *
     * @return true if the grid line is degenerate, false otherwise
     */
    public boolean isDegenerate() {
        return start.equals(end);
    }

    /**
     * Constructs a grid line with the specified start and end points.
     *
     * @param start the starting point of the grid line, must not be null
     * @param end the ending point of the grid line, must not be null
     * @throws IllegalArgumentException if either start or end is null
     */
    public GridLine(GridPoint start, GridPoint end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Grid points cannot be null");
        }

        this.start = start;
        this.end = end;
    }

    /**
     * Calculates the median point of the grid line.
     *
     * @return the median point of the grid line as a {@link Point}
     */
    public Point getMedianPoint(){
        final double medianX = (double) (this.start.x + this.end.x) / 2;
        final double medianY = (double) (this.start.y + this.end.y) / 2;

        return new Point(medianX,medianY);
    }

    /**
     * Calculates the slope of the grid line in degrees.
     *
     * @return the slope of the grid line in degrees
     */
    public double getSlopeCoefficientToDegrees() {
        final double m = this.getSlopeCoefficient();

        if (Double.isInfinite(m)) {
            return (m > 0) ? 90.0 : -90.0;
        }
        return Math.toDegrees(Math.atan(m));
    }

    /**
     * Calculates the slope coefficient of the grid line.
     *
     * @return the slope coefficient of the grid line
     * @throws UnsupportedOperationException if the grid line is degenerate
     */
    public double getSlopeCoefficient(){
        if (isDegenerate()) throw new UnsupportedOperationException("Unsupported on degenerated GridLines");

        double deltaY = this.start.y - this.end.y;
        double deltaX = this.start.x - this.end.x;

        return deltaY / deltaX;
    }

    /**
     * Calculates the intersection points of the grid line with the Cartesian grid.
     *
     * @return a set of intersection points as {@link Point} objects
     * @throws UnsupportedOperationException if the grid line is degenerate
     */
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

    /**
     * Calculates the nearest grid points to the intersection points of the grid line.
     *
     * @return a list of nearest grid points sorted by distance from the start point
     * @throws UnsupportedOperationException if the grid line is degenerate
     */
    public List<GridPoint> getNearestGridPointsOnIntersections(){
        if (isDegenerate()) throw new UnsupportedOperationException("Unsupported on degenerated GridLines");

        Set<Point> intersectionPoints = this.getLineIntersectionsWithGrid();

        Set<GridPoint> intersectionGridPoints = new HashSet<>();

        for (Point intersectionPoint : intersectionPoints) {
            intersectionGridPoints.addAll(intersectionPoint.getNearestDiscretePoints());
        }

        return intersectionGridPoints.stream()
                .sorted(Comparator.comparingDouble(p -> p.distanceTo(this.start)))
                .collect(Collectors.toList());
    }

    /**
     * Returns a string representation of the grid line.
     *
     * @return a string representation of the grid line
     */
    @Override
    public String toString(){
        return "GridLine [start: " + this.start + ", end: " + this.end + "]";
    }
}
