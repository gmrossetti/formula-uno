package com.gmrossetti.mdp.model;

import com.gmrossetti.mdp.controller.PlayerController;

public class GridPoint extends Point {
    public enum GridPointType {
        OUTSIDE,  // Fuori mappa
        INSIDE,   // Dentro la mappa
        START,     // Partenza
        END        // Fine
    }
    public final GridPointType type;
    private PlayerController occupiedBy;
    public final boolean isCurving;
    public final boolean isNarrow;

    public GridPoint(int x, int y, GridPointType type) {
        super(x, y);
        this.type = type;
        this.occupiedBy = null;
        this.isCurving = false;
        this.isNarrow = false;
    }

    public GridPoint(int x, int y, GridPointType type, PlayerController occupiedBy) {
        super(x, y);
        this.type = type;
        this.occupiedBy = occupiedBy;
        this.isCurving = false;
        this.isNarrow = false;
    }
    public GridPoint(int x, int y, GridPointType type, boolean isCurving, boolean isNarrow) {
        super(x, y);
        this.type = type;
        this.occupiedBy = null;
        this.isCurving = isCurving;
        this.isNarrow = isNarrow;
    }

    public GridPoint(GridPoint gridPoint){
        super(gridPoint.x, gridPoint.y);
        this.type = gridPoint.type;
        this.occupiedBy = gridPoint.occupiedBy;
        this.isCurving = gridPoint.isCurving;
        this.isNarrow = gridPoint.isNarrow;
    }

    public boolean isOccupied() {
        return this.occupiedBy != null;
    }
    public void setOccupiedBy(PlayerController occupiedBy) {
        this.occupiedBy = occupiedBy;
    }
    public boolean isWalkable() {
        return this.type != GridPointType.OUTSIDE;
    }
}
