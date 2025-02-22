package com.gmrossetti.mdp.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Waypoint {
    public GridPoint getCenter() {
        return center;
    }
    protected final GridPoint center;
    private final double harshness;
    public double getHarshness() {
        return harshness;
    }
    private final Set<GridPoint> withinRangeGPs;

    private Waypoint next;
    private Waypoint previous;

    public Waypoint(GridPoint center, double harshness) {
        this.center = center;
        this.harshness = harshness;

        this.withinRangeGPs = calcWithinRangeGridPoints();

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

    abstract Set<GridPoint> calcWithinRangeGridPoints();

    public Set<GridPoint> getWithinRangeGridPoints(){
        return withinRangeGPs;
    }

    public boolean isWithinRange(GridPoint gp){
        return withinRangeGPs.contains(gp);
    }

    public boolean isWithinRange(GridLine trace){
        if(isWithinRange(trace.getEnd())) return true;

        if(trace.isDegenerate()) return false;

        List<GridPoint> lastMoveTrailGPs = trace.getNearestGridPointsOnIntersections();

        return this.withinRangeGPs.stream().anyMatch(lastMoveTrailGPs::contains);
    }
}
