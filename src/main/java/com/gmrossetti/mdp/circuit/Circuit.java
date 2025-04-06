package com.gmrossetti.mdp.circuit;

import com.gmrossetti.mdp.circuit.tile.ITile;
import com.gmrossetti.mdp.circuit.tile.Tile;
import com.gmrossetti.mdp.circuit.waypoint.Waypoint;
import com.gmrossetti.mdp.cartesian.GridPoint;

import java.util.*;

class Circuit implements ICircuit {
    private final ITile[][] grid;
    private final ITile raceStartCircuitGridPoint;
    private final List<Waypoint> waypoints;
    private final Waypoint waypointsHead;

    public Circuit(ITile[][] grid, List<Waypoint> waypoints){
        this.grid = grid;
        this.waypoints = waypoints;

        this.waypointsHead = generateWaypointLinkedList(waypoints);

        GridPoint raceStartGridPoint = this.waypointsHead.getCenter();

        this.raceStartCircuitGridPoint = getTile(raceStartGridPoint);
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
    public ITile getTile(int x, int y) {
        try {
            return this.grid[y][x];
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Tile(new GridPoint(x, y),false,null);
        }
    }
    @Override
    public ITile getTile(GridPoint gp){
        return this.getTile(gp.x,gp.y);
    }
    @Override
    public List<ITile> getTile(List<GridPoint> gps) {
        List<ITile> tiles = new ArrayList<>();

        for (GridPoint gp:
                gps) {
            tiles.add(this.getTile(gp));
        }

        return tiles;
    }
    @Override
    public ITile getRaceStartPoint(){
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
    public Waypoint getWaypointsHead() {
        return waypointsHead;
    }
}