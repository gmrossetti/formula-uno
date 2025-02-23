package com.gmrossetti.mdp.actor;

import com.gmrossetti.mdp.entity.waypoint.Waypoint;
import com.gmrossetti.mdp.parser.CircuitParser;
import com.gmrossetti.mdp.entity.cartesian.CircuitGridPoint;
import com.gmrossetti.mdp.entity.cartesian.GridPoint;

import java.io.IOException;
import java.util.*;

public class Circuit {
    private final CircuitGridPoint[][] grid;
    private final CircuitGridPoint raceStartCircuitGridPoint;

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    private final List<Waypoint> waypoints;
    private final Waypoint waypointsHead;

    public Circuit(){
        try {
            this.grid = CircuitParser.parseImageToGrid("circuit1");
            this.waypoints = CircuitParser.parseWaypointsJson("circuit1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

    public CircuitGridPoint getGridPoint(int x, int y) {
        try {
            CircuitGridPoint circuitGridPoint = this.grid[y][x];
            return new CircuitGridPoint(circuitGridPoint);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new CircuitGridPoint(x, y, CircuitGridPoint.GridPointType.OUTSIDE);
        }
    }

    public CircuitGridPoint getGridPoint(GridPoint gp){
        return this.getGridPoint(gp.x,gp.y);
    }

    public CircuitGridPoint getRaceStartPoint(){
        return this.raceStartCircuitGridPoint;
    }

    public int getGridWidth(){
        return this.grid[0].length;
    }

    public int getGridHeight(){
        return this.grid.length;
    }

    public List<CircuitGridPoint> toCircuitGridPoint(List<GridPoint> points) {
        if (points == null) {
            throw new IllegalArgumentException("Points collection cannot be null");
        }

        List<CircuitGridPoint> result = new ArrayList<>(points.size());
        points.forEach(p -> result.add(new CircuitGridPoint(this.getGridPoint(p))));
        return result;
    }

    public CircuitGridPoint toCircuitGridPoint(GridPoint point) {
        if (point == null) {
            throw new IllegalArgumentException("Point cannot be null");
        }
        return new CircuitGridPoint(this.getGridPoint(point));
    }

    public Waypoint getWaypointsHead() {
        return waypointsHead;
    }
}