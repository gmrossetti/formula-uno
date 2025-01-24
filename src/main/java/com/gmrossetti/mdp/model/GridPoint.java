package com.gmrossetti.mdp.model;

public class GridPoint extends Point {
    public enum GridPointType {
        OUTSIDE,  // Fuori mappa
        INSIDE,           // Dentro la mappa
        START
    }
    public final GridPointType type;
    private Player occupiedBy;

    public GridPoint(int x, int y, GridPointType type) {
        super(x, y);
        this.type = type;
        this.occupiedBy = null;
    }

    public GridPoint(int x, int y, GridPointType type, Player occupiedBy) {
        super(x, y);
        this.type = type;
        this.occupiedBy = occupiedBy;
    }

    public GridPoint(GridPoint gridPoint){
        super(gridPoint.x, gridPoint.y);
        this.type = gridPoint.type;
        this.occupiedBy = gridPoint.occupiedBy;
    }

    public boolean isBusy() {
        return this.occupiedBy != null;
    }
    public void setOccupiedBy(Player occupiedBy) {
        this.occupiedBy = occupiedBy;
    }
}
