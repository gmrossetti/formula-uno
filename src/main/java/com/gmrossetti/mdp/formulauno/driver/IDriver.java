package com.gmrossetti.mdp.formulauno.driver;

import com.gmrossetti.mdp.formulauno.cartesian.GridLine;
import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.Waypoint;
import com.gmrossetti.mdp.formulauno.driver.move.Move;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;

import java.util.Map;

/**
 * The IDriver interface represents a driver in the Formula Uno game.
 * It provides methods to get the pawn, check for active waypoints,
 * retrieve the target waypoint, make a move, and get move points.
 */
public interface IDriver {
    /**
     * Gets the pawn associated with this driver.
     *
     * @return The pawn of the driver.
     */
    IPawn getPawn();

    /**
     * Checks if the driver has an active waypoint.
     *
     * @return True if there is an active waypoint, false otherwise.
     */
    boolean hasActiveWaypoint();

    /**
     * Gets the target waypoint for the driver.
     *
     * @return The target waypoint.
     */
    Waypoint getWaypointTarget();

    /**
     * Makes a move for the driver.
     *
     * @return The grid line representing the move.
     */
    GridLine makeMove();

    /**
     * Gets the points associated with the moves.
     *
     * @return A map of moves to their corresponding grid points.
     */
    Map<Move, GridPoint> getMovesPoints();
}