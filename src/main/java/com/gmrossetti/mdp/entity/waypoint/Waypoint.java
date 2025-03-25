package com.gmrossetti.mdp.entity.waypoint;

import com.gmrossetti.mdp.entity.cartesian.GridLine;
import com.gmrossetti.mdp.entity.cartesian.GridPoint;

import java.util.List;
import java.util.Set;

public abstract class Waypoint {
    public GridPoint getCenter() {
        return center;
    }
    protected final GridPoint center;
    protected Set<GridPoint> withinRangeGPs;

    private Waypoint next;
    private Waypoint previous;

    public Waypoint(GridPoint center) {
        this.center = center;
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

    abstract void initWithinRangeGridPoints();

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
