package com.gmrossetti.mdp.formulauno.cartesian;

import java.util.Objects;

/**
 * Abstract base class representing a point in a Cartesian coordinate system.
 * This class enforces the use of numeric types (Integer or Double) for coordinates
 * and provides common functionality such as distance calculation, equality checks,
 * and string representation.
 *
 * @param <T> The type of the coordinates, restricted to Integer or Double.
 */
public abstract class AbstractPoint<T extends Number> {

    /** The x-coordinate of the point. */
    public final T x;

    /** The y-coordinate of the point. */
    public final T y;

    /**
     * Constructs an AbstractPoint with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the point, must be an instance of Integer or Double
     * @param y the y-coordinate of the point, must be an instance of Integer or Double
     * @throws IllegalArgumentException if x or y is not an instance of Integer or Double
     */
    public AbstractPoint(T x, T y) {
        if (!(x instanceof Integer || x instanceof Double)) {
            throw new IllegalArgumentException("Only Integer and Double are allowed");
        }

        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the Euclidean distance from this point to another point.
     *
     * @param other the other point to calculate the distance to
     * @return the Euclidean distance between this point and the other point
     */
    public double distanceTo(AbstractPoint<?> other) {
        return Math.sqrt(Math.pow(other.x.doubleValue() - this.x.doubleValue(), 2)
                + Math.pow(other.y.doubleValue() - this.y.doubleValue(), 2));
    }

    /**
     * Checks if this point is equal to another object.
     * Two points are considered equal if their x and y coordinates are equal.
     *
     * @param o the object to compare with
     * @return true if the object is an AbstractPoint with the same coordinates, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractPoint<?> that)) return false;
        return x.equals(that.x) && y.equals(that.y);
    }

    /**
     * Computes the hash code for this point based on its coordinates.
     *
     * @return the hash code of this point
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Returns a string representation of this point in the format "(x, y)".
     *
     * @return the string representation of this point
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}