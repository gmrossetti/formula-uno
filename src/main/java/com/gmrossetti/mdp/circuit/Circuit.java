package com.gmrossetti.mdp.circuit;

import com.gmrossetti.mdp.circuit.waypoint.Waypoint;
import com.gmrossetti.mdp.cartesian.CircuitGridPoint;
import com.gmrossetti.mdp.cartesian.GridPoint;

import java.util.*;

class Circuit implements ICircuit {
    private final CircuitGridPoint[][] grid;
    private final CircuitGridPoint raceStartCircuitGridPoint;
    private final List<Waypoint> waypoints;
    private final Waypoint waypointsHead;

    public Circuit(CircuitGridPoint[][] grid, List<Waypoint> waypoints){
        this.grid = grid;
        this.waypoints = waypoints;

        this.waypointsHead = generateWaypointLinkedList(waypoints);

        GridPoint raceStartGridPoint = this.waypointsHead.getCenter();

        this.raceStartCircuitGridPoint = this.toCircuitGridPoint(raceStartGridPoint);
    }

    private static Waypoint generateWaypointLinkedList(List<Waypoint> waypoints){
        List<Waypoint> waypointsCopy = new ArrayList<>(waypoints);

        Waypoint head = waypointsCopy.remove(0);

        Waypoint currentWp = head;

        for (Waypoint wp:
                waypointsCopy) {
            currentWp.insertAfter(wp);
            currentWp = wp;
        }

        return head;
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return waypoints;
    }
    @Override
    public CircuitGridPoint getGridPoint(int x, int y) {
        try {
            CircuitGridPoint circuitGridPoint = this.grid[y][x];
            return new CircuitGridPoint(circuitGridPoint);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new CircuitGridPoint(x, y, CircuitGridPoint.GridPointType.OUTSIDE);
        }
    }
    @Override
    public CircuitGridPoint getGridPoint(GridPoint gp){
        return this.getGridPoint(gp.x,gp.y);
    }
    @Override
    public CircuitGridPoint getRaceStartPoint(){
        return this.raceStartCircuitGridPoint;
    }
    @Override
    public int getGridWidth(){
        return this.grid[0].length;
    }
    @Override
    public int getGridHeight(){
        return this.grid.length;
    }
    @Override
    public List<CircuitGridPoint> toCircuitGridPoint(List<GridPoint> points) {
        if (points == null) {
            throw new IllegalArgumentException("Points collection cannot be null");
        }

        List<CircuitGridPoint> result = new ArrayList<>(points.size());
        points.forEach(p -> result.add(new CircuitGridPoint(this.getGridPoint(p))));
        return result;
    }
    @Override
    public CircuitGridPoint toCircuitGridPoint(GridPoint point) {
        if (point == null) {
            throw new IllegalArgumentException("Point cannot be null");
        }
        return new CircuitGridPoint(this.getGridPoint(point));
    }
    @Override
    public Waypoint getWaypointsHead() {
        return waypointsHead;
    }
}