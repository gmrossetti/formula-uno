package com.gmrossetti.mdp.model;

import com.gmrossetti.mdp.actor.Car;

public class CircuitGridPoint extends GridPoint {
    public enum GridPointType {
        OUTSIDE,  // Fuori mappa
        INSIDE,   // Dentro la mappa
        START,     // Partenza
        END        // Fine
    }
    public final GridPointType type;
    private Car occupiedBy;
    public final boolean isCurving;
    public final boolean isNarrow;

    public CircuitGridPoint(int x, int y, GridPointType type) {
        super(x, y);
        this.type = type;
        this.occupiedBy = null;
        this.isCurving = false;
        this.isNarrow = false;
    }

    public CircuitGridPoint(int x, int y, GridPointType type, Car occupiedBy) {
        super(x, y);
        this.type = type;
        this.occupiedBy = occupiedBy;
        this.isCurving = false;
        this.isNarrow = false;
    }
    public CircuitGridPoint(int x, int y, GridPointType type, boolean isCurving, boolean isNarrow) {
        super(x, y);
        this.type = type;
        this.occupiedBy = null;
        this.isCurving = isCurving;
        this.isNarrow = isNarrow;
    }

    public CircuitGridPoint(CircuitGridPoint circuitGridPoint){
        super(circuitGridPoint.x, circuitGridPoint.y);
        this.type = circuitGridPoint.type;
        this.occupiedBy = circuitGridPoint.occupiedBy;
        this.isCurving = circuitGridPoint.isCurving;
        this.isNarrow = circuitGridPoint.isNarrow;
    }

    public boolean isOccupied() {
        return this.occupiedBy != null;
    }
    public void setOccupiedBy(Car occupiedBy) {
        this.occupiedBy = occupiedBy;
    }
    public boolean isWalkable() {
        return this.type != GridPointType.OUTSIDE;
    }
}
