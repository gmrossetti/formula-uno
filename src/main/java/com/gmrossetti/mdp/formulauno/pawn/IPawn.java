package com.gmrossetti.mdp.formulauno.pawn;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;

import java.util.List;

/**
 * Interface representing a pawn in the game.
 * It provides methods to get the pawn's position, trail, velocity, and to move the pawn.
 */
public interface IPawn {
    /**
     * Gets the current position of the pawn.
     *
     * @return The current position of the pawn as a GridPoint.
     */
    GridPoint getPosition();

    /**
     * Gets the trail of the pawn, which is a list of GridPoints representing the path taken.
     *
     * @return A list of GridPoints representing the trail of the pawn.
     */
    List<GridPoint> getTrail();

    /**
     * Checks if the pawn is stationary.
     *
     * @return True if the pawn is stationary, false otherwise.
     */
    boolean isStationary();

    /**
     * Gets the velocity vector of the pawn.
     *
     * @return The velocity vector of the pawn as a GridPoint.
     */
    GridPoint getVelocityVector();

    /**
     * Gets the module (magnitude) of the velocity vector of the pawn.
     *
     * @return The module of the velocity vector as a double.
     */
    double getVelocityModule();

    /**
     * Moves the pawn to a new position.
     *
     * @param position The new position to move the pawn to as a GridPoint.
     */
    void move(GridPoint position);

    /**
     * Gets the pivot point of the pawn.
     *
     * @return The pivot point of the pawn as a GridPoint.
     */
    GridPoint getPivot();
}
