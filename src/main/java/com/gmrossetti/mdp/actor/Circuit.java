package com.gmrossetti.mdp.actor;

import com.gmrossetti.mdp.entity.Waypoint;
import com.gmrossetti.mdp.level.LevelParser;
import com.gmrossetti.mdp.entity.CircuitGridPoint;
import com.gmrossetti.mdp.entity.GridPoint;

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
        waypoints.add(new Waypoint(new GridPoint(18,5), 5,0.2)); // start point
        waypoints.add(new Waypoint(new GridPoint(47, 5), 5,0.2));
        waypoints.add(new Waypoint(new GridPoint(53, 11), 5,0.2));
        waypoints.add(new Waypoint(new GridPoint(49, 18), 5,0.2));
        waypoints.add(new Waypoint(new GridPoint(55, 32), 5,0.2));
        waypoints.add(new Waypoint(new GridPoint(36, 39), 5,0.2));
        waypoints.add(new Waypoint(new GridPoint(32, 28), 5,0.2));
        waypoints.add(new Waypoint(new GridPoint(23, 24), 5,0.2));
        waypoints.add(new Waypoint(new GridPoint(10, 29), 5,0.2));
        waypoints.add(new Waypoint(new GridPoint(4, 22), 5,0.2));
        waypoints.add(new Waypoint(new GridPoint(3, 12), 5,0.2));
        waypoints.add(new Waypoint(new GridPoint(8, 5), 5,0.2));
        waypoints.add(new Waypoint(new GridPoint(17, 5), 5,0.2)); // end point

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
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                CircuitGridPoint circuitGridPoint = grid[y][x];

                if (circuitGridPoint.type == CircuitGridPoint.GridPointType.START) {
                    this.raceStartLine.add(circuitGridPoint);
                }
            }
        }

        if(raceStartLine.isEmpty()){
            throw new RuntimeException("Race start line not provided.");
        }
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