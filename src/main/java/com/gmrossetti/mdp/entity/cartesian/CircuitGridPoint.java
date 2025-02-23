package com.gmrossetti.mdp.entity.cartesian;

import com.gmrossetti.mdp.actor.Car;

public class CircuitGridPoint extends GridPoint {
    public enum GridPointType {
        OUTSIDE,  // Fuori mappa
        INSIDE,   // Dentro la mappa
    }
    public final GridPointType type;
    private Car occupiedBy;

    public CircuitGridPoint(int x, int y, GridPointType type) {
        super(x, y);
        this.type = type;
        this.occupiedBy = null;
    }

    public CircuitGridPoint(int x, int y, GridPointType type, Car occupiedBy) {
        super(x, y);
        this.type = type;
        this.occupiedBy = occupiedBy;
    }

    public CircuitGridPoint(CircuitGridPoint circuitGridPoint){
        super(circuitGridPoint.x, circuitGridPoint.y);
        this.type = circuitGridPoint.type;
        this.occupiedBy = circuitGridPoint.occupiedBy;
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
