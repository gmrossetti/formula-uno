package com.gmrossetti.mdp.entity;

import com.gmrossetti.mdp.driver.CarDriver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Waypoint {
    public GridPoint getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    private final GridPoint center;
    private final int radius;
    private final double harshness;
    public double getHarshness() {
        return harshness;
    }
    private final Set<GridPoint> withinRadiusGPs;

    private Waypoint next;
    private Waypoint previous;

    public Waypoint(GridPoint center, int radius, double harshness) {
        this.center = center;
        this.radius = radius;
        this.harshness = harshness;

        this.withinRadiusGPs = calcWithinRadiusGPs();

        this.next = null;
        this.previous = null;
    }

    public void insertAfter(Waypoint newWaypoint) {
        newWaypoint.next = this.next;
        if (this.hasNext()) {
            this.next.previous = newWaypoint;
        }
        this.next = newWaypoint;
        newWaypoint.previous = this;
    }

    public boolean hasNext(){
        return next != null;
    }

    public boolean hasPrevious(){
        return previous != null;
    }

    public Waypoint getNext() {
        return next;
    }

    public Waypoint getPrevious() {
        return previous;
    }

    private Set<GridPoint> calcWithinRadiusGPs(){
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

    public Set<GridPoint> getWithinRadiusGPs(){
        return withinRadiusGPs;
    }

    public boolean isWithinRadius(GridPoint gp){
        return withinRadiusGPs.contains(gp);
    }

    public boolean isWithinRadius(GridLine trace){
        if(isWithinRadius(trace.getEnd())) return true;

        if(trace.isDegenerate()) return false;

        List<GridPoint> lastMoveTrailGPs = trace.getNearestGridPointsOnIntersections();

        return this.getWithinRadiusGPs().stream().anyMatch(lastMoveTrailGPs::contains);
    }
}
