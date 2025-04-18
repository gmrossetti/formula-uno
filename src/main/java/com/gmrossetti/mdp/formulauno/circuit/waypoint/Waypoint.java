package com.gmrossetti.mdp.formulauno.circuit.waypoint;

import com.gmrossetti.mdp.formulauno.cartesian.GridLine;
import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;

import java.util.List;
import java.util.Set;

/**
 * Represents a waypoint in the circuit.
 * A waypoint is defined by its center point and the grid points within its range.
 */
public abstract class Waypoint {
    /**
     * The center point of the waypoint.
     */
    public GridPoint getCenter() {
        return center;
    }
    protected final GridPoint center;

    /**
     * The set of grid points within the range of the waypoint.
     */
    protected Set<GridPoint> withinRangeGPs;

    /**
     * The next waypoint in the linked list.
     */
    private Waypoint next;

    /**
     * The previous waypoint in the linked list.
     */
    private Waypoint previous;

    /**
     * Constructor for creating a Waypoint.
     *
     * @param center The center point of the waypoint.
     */
    public Waypoint(GridPoint center) {
        this.center = center;
        this.next = null;
        this.previous = null;
    }

    /**
     * Sets the next waypoint in the linked list.
     *
     * @param newWaypoint The next waypoint to set.
     */
    public void insertAfter(Waypoint newWaypoint) {
        newWaypoint.next = this.next;
        if (this.hasNext()) {
            this.next.previous = newWaypoint;
        }
        this.next = newWaypoint;
        newWaypoint.previous = this;
    }

    /**
     * Checks if the waypoint it has a next Waypoint.
     *
     * @return true if it has next waypoint, false otherwise
     */
    public boolean hasNext(){
        return next != null;
    }

    /**
     * Checks if the waypoint it has a previous Waypoint.
     *
     * @return true if it has previous waypoint, false otherwise
     */
    public boolean hasPrevious(){
        return previous != null;
    }

    /**
     * Gets the next waypoint in the linked list.
     *
     * @return next The next waypoint.
     */
    public Waypoint getNext() {
        return next;
    }

    /**
     * Gets the previous waypoint in the linked list.
     *
     * @return previous The previous waypoint.
     */
    public Waypoint getPrevious() {
        return previous;
    }

    /**
     * Initializes the set of grid points within the range of the waypoint.
     */
    abstract void initWithinRangeGridPoints();

    /**
     * Checks if the waypoint is within range of a given grid point.
     *
     * @param gp The grid point to check against.
     * @return true if the waypoint is within range of the grid point, false otherwise.
     */
    public boolean isWithinRange(GridPoint gp){
        return withinRangeGPs.contains(gp);
    }

    /**
     * Checks if the waypoint is within range of a given grid line.
     *
     * @param trace The grid line to check against.
     * @return true if the waypoint is within range of the grid line, false otherwise.
     */
    public boolean isWithinRange(GridLine trace){
        if(isWithinRange(trace.getEnd())) return true;

        if(trace.isDegenerate()) return false;

        List<GridPoint> lastMoveTrailGPs = trace.getNearestGridPointsOnIntersections();

        return this.withinRangeGPs.stream().anyMatch(lastMoveTrailGPs::contains);
    }
}
