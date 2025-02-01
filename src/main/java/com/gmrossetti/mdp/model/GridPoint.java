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

    public GridPoint(int x, int y, GridPointType type) {
        super(x, y);
        this.type = type;
        this.occupiedBy = null;
    }

    public GridPoint(int x, int y, GridPointType type, PlayerController occupiedBy) {
        super(x, y);
        this.type = type;
        this.occupiedBy = occupiedBy;
    }

    public GridPoint(GridPoint gridPoint){
        super(gridPoint.x, gridPoint.y);
        this.type = gridPoint.type;
        this.occupiedBy = gridPoint.occupiedBy;
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
