package com.gmrossetti.mdp.circuit.waypoint;

import com.gmrossetti.mdp.cartesian.GridPoint;

import java.util.HashSet;
import java.util.Set;

public class MidWaypoint extends Waypoint {
    private final int radius;
    public int getRadius() {
        return radius;
    }

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
