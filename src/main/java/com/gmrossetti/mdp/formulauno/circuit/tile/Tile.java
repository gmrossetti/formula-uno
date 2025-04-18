package com.gmrossetti.mdp.formulauno.circuit.tile;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;

/**
 * Class representing a tile on the circuit.
 * A tile can be occupied by a pawn and has a specific grid point.
 */
public class Tile implements ITile {
    /**
     * The grid point of the tile.
     */
    private final GridPoint gridPoint;
    /**
     * Indicates if the tile is on track.
     */
    private final boolean isOnTrack;
    /**
     * The pawn occupying the tile.
     */
    private IPawn occupiedBy;

    /**
     * Constructor for creating a tile with a grid point and track status.
     *
     * @param gridPoint the grid point of the tile
     * @param isOnTrack true if the tile is on track, false otherwise
     */
    public Tile(GridPoint gridPoint, boolean isOnTrack) {
        this.gridPoint = gridPoint;
        this.isOnTrack = isOnTrack;
        this.occupiedBy = null;
    }

    /**
     * Constructor for creating a tile with a grid point, track status, and an occupying pawn.
     *
     * @param gridPoint the grid point of the tile
     * @param isOnTrack true if the tile is on track, false otherwise
     * @param occupiedBy the pawn occupying the tile
     */
    public Tile(GridPoint gridPoint, boolean isOnTrack, IPawn occupiedBy) {
        this.gridPoint = gridPoint;
        this.isOnTrack = isOnTrack;
        this.occupiedBy = occupiedBy;
    }
    @Override
    public GridPoint getGridPoint() {
        return gridPoint;
    }
    @Override
    public boolean isOnTrack() {
        return isOnTrack;
    }
    @Override
    public IPawn getOccupiedBy() {
        return occupiedBy;
    }
    @Override
    public boolean isOccupied() {
        return this.occupiedBy != null;
    }
    @Override
    public void setOccupiedBy(IPawn occupiedBy) {
        this.occupiedBy = occupiedBy;
    }
}
