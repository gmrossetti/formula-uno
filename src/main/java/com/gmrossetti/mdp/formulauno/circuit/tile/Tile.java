package com.gmrossetti.mdp.formulauno.circuit.tile;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;

public class Tile implements ITile {
    private final GridPoint gridPoint;
    private final boolean isOnTrack;
    private IPawn occupiedBy;

    public Tile(GridPoint gridPoint, boolean isOnTrack) {
        this.gridPoint = gridPoint;
        this.isOnTrack = isOnTrack;
        this.occupiedBy = null;
    }
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
