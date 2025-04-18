package com.gmrossetti.mdp.formulauno.cartesian;

import java.util.HashSet;
import java.util.Set;

/**
 * Point class representing a point in a 2D Cartesian coordinate system.
 * It extends the AbstractPoint class and provides methods for point operations.
 */
public class Point extends AbstractPoint<Double> {
    /**
     * Constructor for creating a Point object with specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @throws IllegalArgumentException if x or y is not finite
     */
    public Point(double x, double y) {
        super(x, y);
        if (!Double.isFinite(x) || !Double.isFinite(y)) {
            throw new IllegalArgumentException("x and y must be finite. x: " + x + ", y: " + y);
        }
    }

    /**
     * Copy constructor for creating a Point object from another Point object.
     *
     * @param point the Point object to copy
     */
    public Point(Point point) {
        super(point.x,point.y);
    }

    /**
     * Sums the current point with another point and returns the resulting point.
     *
     * @param point2sum the point to add to the current point
     * @return a new Point representing the sum of the two points
     */
    public Point sum(Point point2sum){
        if(point2sum == null) return new Point(this);

        double newX = this.x + point2sum.x;
        double newY = this.y + point2sum.y;

        return new Point(newX, newY);
    }

    /**
     * Subtracts another point from the current point and returns the resulting point.
     *
     * @param point2sub the point to subtract from the current point
     * @return a new Point representing the difference of the two points
     */
    public Point sub(Point point2sub){
        if(point2sub == null) return new Point(this);

        double newX = this.x - point2sub.x;
        double newY = this.y - point2sub.y;

        return new Point(newX, newY);
    }

    /**
     * Calculates the set of nearest integer grid points to the current point.
     * The method considers the floor and ceiling values of the x and y coordinates
     * to determine the closest integer points in the Cartesian grid.
     *
     * @return a set of {@link GridPoint} objects representing the nearest integer grid points
     */
    public Set<GridPoint> getNearestDiscretePoints(){
        int floorX = (int) Math.floor(this.x);
        int ceilX = (int) Math.ceil(this.x);

        int floorY = (int) Math.floor(this.y);
        int ceilY = (int) Math.ceil(this.y);

        Set<GridPoint> closestIntegerPoints = new HashSet<>();

        closestIntegerPoints.add(new GridPoint(floorX,floorY));
        closestIntegerPoints.add(new GridPoint(floorX,ceilY));
        closestIntegerPoints.add(new GridPoint(ceilX,floorY));
        closestIntegerPoints.add(new GridPoint(ceilX,ceilY));

        return closestIntegerPoints;
    }
}