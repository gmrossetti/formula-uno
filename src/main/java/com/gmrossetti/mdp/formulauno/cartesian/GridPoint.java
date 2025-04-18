package com.gmrossetti.mdp.formulauno.cartesian;

/**
 * Represents a point in a Cartesian grid with integer coordinates.
 * Provides methods for basic arithmetic operations on grid points.
 */
public class GridPoint extends AbstractPoint<Integer> {
    /**
     * Represents a point in a Cartesian grid with integer coordinates.
     * Extends {@link AbstractPoint} with integer-specific operations.
     * Provides methods for basic arithmetic operations on grid points.
     */
    public GridPoint(int x, int y) {
        super(x, y);
    }

    /**
     * Copy constructor for creating a new GridPoint from an existing one.
     *
     * @param gridPoint The GridPoint to copy.
     */
    public GridPoint(GridPoint gridPoint) {
        super(gridPoint.x, gridPoint.y);
    }

    /**
     * Default constructor for creating a GridPoint at the origin (0, 0).
     *
     * @param point2sum The GridPoint to copy.
     * @return A new GridPoint representing the sum of the two points.
     */
    public GridPoint sum(GridPoint point2sum){
        if(point2sum == null) return new GridPoint(this);

        int newX = this.x + point2sum.x;
        int newY = this.y + point2sum.y;

        return new GridPoint(newX, newY);
    }

    /**
     * Subtracts the coordinates of another GridPoint from this one.
     *
     * @param point2sub The GridPoint to subtract.
     * @return A new GridPoint representing the result of the subtraction.
     */
    public GridPoint sub(GridPoint point2sub){
        if(point2sub == null) return new GridPoint(this);

        int newX = this.x - point2sub.x;
        int newY = this.y - point2sub.y;

        return new GridPoint(newX, newY);
    }
}
