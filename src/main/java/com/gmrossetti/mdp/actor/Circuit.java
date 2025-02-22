package com.gmrossetti.mdp.actor;

import com.gmrossetti.mdp.entity.waypoint.BoundaryWaypoint;
import com.gmrossetti.mdp.entity.waypoint.MidWaypoint;
import com.gmrossetti.mdp.entity.waypoint.Waypoint;
import com.gmrossetti.mdp.level.LevelParser;
import com.gmrossetti.mdp.entity.cartesian.CircuitGridPoint;
import com.gmrossetti.mdp.entity.cartesian.GridPoint;

import java.io.IOException;
import java.util.*;

public class Circuit {
    private final CircuitGridPoint[][] grid;
    private final List<CircuitGridPoint> raceStartLine;
    private final CircuitGridPoint raceStartCircuitGridPoint;

    public ArrayList<Waypoint> getWaypoints() {
        return waypoints;
    }

    private final ArrayList<Waypoint> waypoints;
    private final Waypoint waypointsHead;

    public Circuit(){
        try {
            this.grid = LevelParser.parseImageToGrid("circuit1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.raceStartLine = new ArrayList<>();
        initRaceStartEndLines();

        raceStartCircuitGridPoint = raceStartLine.get(raceStartLine.size() / 2);

        this.waypoints = new ArrayList<>();

        // TODO: refactor, waypoints are hardcoded
        waypoints.add(new BoundaryWaypoint(raceStartCircuitGridPoint, 0.2, 1,7, BoundaryWaypoint.Type.START)); // start point
        waypoints.add(new MidWaypoint(new GridPoint(47, 5), 0.2, 5));
        waypoints.add(new MidWaypoint(new GridPoint(53, 11), 0.2, 5));
        waypoints.add(new MidWaypoint(new GridPoint(49, 18), 0.2, 5));
        waypoints.add(new MidWaypoint(new GridPoint(55, 32), 0.2, 4));
        waypoints.add(new MidWaypoint(new GridPoint(36, 39), 0.2, 5));
        waypoints.add(new MidWaypoint(new GridPoint(32, 28), 0.2, 5));
        waypoints.add(new MidWaypoint(new GridPoint(23, 24), 0.2, 4));
        waypoints.add(new MidWaypoint(new GridPoint(10, 30), 0.2, 5));
        waypoints.add(new MidWaypoint(new GridPoint(4, 22), 0.2, 3));
        waypoints.add(new MidWaypoint(new GridPoint(3, 12), 0.2, 3));
        waypoints.add(new MidWaypoint(new GridPoint(8, 5), 0.2, 5));
        waypoints.add(new BoundaryWaypoint(new GridPoint(raceStartCircuitGridPoint.x - 1, raceStartCircuitGridPoint.y), 0.2, 1,7, BoundaryWaypoint.Type.FINISH)); // end point

        this.waypointsHead = generateWaypointLinkedList(waypoints);
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

    private void initRaceStartEndLines() {
        // TODO: make it dynamic based on json file
        this.raceStartLine.add(new CircuitGridPoint(18,1, CircuitGridPoint.GridPointType.INSIDE));
        this.raceStartLine.add(new CircuitGridPoint(18,2, CircuitGridPoint.GridPointType.INSIDE));
        this.raceStartLine.add(new CircuitGridPoint(18,3, CircuitGridPoint.GridPointType.INSIDE));
        this.raceStartLine.add(new CircuitGridPoint(18,4, CircuitGridPoint.GridPointType.INSIDE));
        this.raceStartLine.add(new CircuitGridPoint(18,5, CircuitGridPoint.GridPointType.INSIDE));
        this.raceStartLine.add(new CircuitGridPoint(18,6, CircuitGridPoint.GridPointType.INSIDE));
        this.raceStartLine.add(new CircuitGridPoint(18,7, CircuitGridPoint.GridPointType.INSIDE));
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