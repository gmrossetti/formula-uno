package com.gmrossetti.mdp.formulauno.circuit.tile;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;

/**
 * Interface representing a tile on the circuit.
 * A tile can be occupied by a pawn and has a specific grid point.
 */
public interface ITile {
    /**
     * Gets the grid point of the tile.
     *
     * @return the grid point of the tile
     */
    GridPoint getGridPoint();
    /**
     * Checks if the tile is on track.
     *
     * @return true if the tile is on track, false otherwise
     */
    boolean isOnTrack();

    /**
     * Gets the pawn occupying the tile.
     *
     * @return IPawn object representing the pawn occupying the tile, null if not occupied
     */
    IPawn getOccupiedBy();

    /**
     * Checks if the tile is occupied by another player/Pawn.
     *
     * @return true if the tile is occupied, false otherwise
     */
    boolean isOccupied();

    /**
     * Sets the tile to be occupied by a pawn.
     *
     * @param occupiedBy the pawn occupying the tile
     */
    void setOccupiedBy(IPawn occupiedBy);
}
