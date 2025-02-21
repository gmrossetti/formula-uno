package com.gmrossetti.mdp.entity;

import java.util.HashSet;
import java.util.Set;

public class Waypoint {
    private final GridPoint center;
    private final int radius;
    private final double harshness;
    public double getHarshness() {
        return harshness;
    }
    private final Set<GridPoint> withinRadiusGPs;

    public Waypoint(GridPoint center, int radius, double harshness) {
        this.center = center;
        this.radius = radius;
        this.harshness = harshness;

        this.withinRadiusGPs = getWithinRadiusGPs();
    }

    private Set<GridPoint> getWithinRadiusGPs(){
        final int x0 = this.center.x;
        final int y0 = this.center.y;

        final Set<GridPoint> withinRadiusGPs = new HashSet<>();

        for (int x = x0 - radius; x <= x0 + radius; x++) {
            for (int y = y0 - radius; y <= y0 + radius; y++) {
                // Controlla se il punto (x, y) è all'interno del cerchio
                if ((Math.pow(x - x0, 2) + Math.pow(y - y0, 2)) <= Math.pow(radius, 2)) {
                    withinRadiusGPs.add(new GridPoint(x, y));
                }
            }
        }

        return withinRadiusGPs;
    }

    public boolean isWithinRadius(GridPoint gp){
        return withinRadiusGPs.contains(gp);
    }
}
