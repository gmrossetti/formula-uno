package com.gmrossetti.mdp.formulauno.circuit;

import com.gmrossetti.mdp.formulauno.circuit.tile.ITile;
import com.gmrossetti.mdp.formulauno.circuit.tile.Tile;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.Waypoint;
import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;

import java.util.*;

/**
 * Represents a circuit composed of tiles and waypoints.
 * The circuit is defined by a grid of tiles and a list of waypoints.
 */
class Circuit implements ICircuit {
    private final ITile[][] grid;
    private final ITile raceStartCircuitGridPoint;
    private final List<Waypoint> waypoints;
    private final Waypoint waypointsHead;

    /**
     * Constructor for creating a Circuit.
     *
     * @param grid      The grid of tiles representing the circuit.
     * @param waypoints The list of waypoints in the circuit.
     */
    public Circuit(ITile[][] grid, List<Waypoint> waypoints){
        this.grid = grid;
        this.waypoints = waypoints;

        this.waypointsHead = generateWaypointLinkedList(waypoints);

        GridPoint raceStartGridPoint = this.waypointsHead.getCenter();

        this.raceStartCircuitGridPoint = getTile(raceStartGridPoint);
    }

    /**
     * Generates a linked list of waypoints from the given list of waypoints.
     * The first waypoint in the list becomes the head of the linked list.
     *
     * @param waypoints The list of waypoints to be converted into a linked list.
     * @return The head of the linked list of waypoints.
     */
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