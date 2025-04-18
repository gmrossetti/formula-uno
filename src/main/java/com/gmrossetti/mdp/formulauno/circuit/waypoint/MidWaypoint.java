package com.gmrossetti.mdp.formulauno.circuit.waypoint;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a mid waypoint in the circuit.
 * A mid waypoint is defined by its center point and radius.
 * It also calculates the grid points within its range based on its radius.
 */
public class MidWaypoint extends Waypoint {
    private final int radius;

    /**
     * The radius of the mid waypoint.
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Constructor for creating a MidWaypoint.
     *
     * @param center The center point of the mid waypoint.
     * @param radius The radius of the mid waypoint.
     */
    public MidWaypoint(GridPoint center, int radius){
        super(center);

        this.radius = radius;

        initWithinRangeGridPoints();
    }

    @Override
    void initWithinRangeGridPoints() {
        final int x0 = center.x;
        final int y0 = center.y;

        final Set<GridPoint> withinRangeGPs = new HashSet<>();

        for (int x = x0 - radius; x <= x0 + radius; x++) {
            for (int y = y0 - radius; y <= y0 + radius; y++) {
                // Controlla se il punto (x, y) è all'interno del cerchio
                if ((Math.pow(x - x0, 2) + Math.pow(y - y0, 2)) <= Math.pow(radius, 2)) {
                    withinRangeGPs.add(new GridPoint(x, y));
                }
            }
        }

        this.withinRangeGPs = withinRangeGPs;
    }
}
