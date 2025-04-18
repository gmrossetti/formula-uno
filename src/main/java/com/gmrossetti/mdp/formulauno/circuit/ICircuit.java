package com.gmrossetti.mdp.formulauno.circuit;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.circuit.tile.ITile;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.Waypoint;

import java.util.List;

/**
 * Interface representing a circuit composed of tiles and waypoints.
 * The circuit is defined by a grid of tiles and a list of waypoints.
 */
public interface ICircuit {
    /**
     * Returns the list of waypoints in the circuit.
     *
     * @return List of waypoints.
     */
    List<Waypoint> getWaypoints();

    /**
     * Returns the tile at the specified coordinates (x, y).
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @return The tile at the specified coordinates.
     */
    ITile getTile(int x, int y);

    /**
     * Returns the tile at the specified grid point.
     *
     * @param gp The grid point of the tile.
     * @return The tile at the specified grid point.
     */
    ITile getTile(GridPoint gp);

    /**
     * Returns the list of tiles at the specified grid points.
     *
     * @param gps The list of grid points.
     * @return The list of tiles at the specified grid points.
     */
    List<ITile> getTile(List<GridPoint> gps);

    /**
     * Returns the tile race start point.
     *
     * @return The tile at race start point.
     */
    ITile getRaceStartPoint();

    /**
     * Returns the width of the grid.
     *
     * @return The width of the grid.
     */
    int getGridWidth();

    /**
     * Returns the height of the grid.
     *
     * @return The height of the grid.
     */
    int getGridHeight();

    /**
     * Returns the head of the waypoints linked list.
     *
     * @return The head of the waypoints linked list.
     */
    Waypoint getWaypointsHead();
}
