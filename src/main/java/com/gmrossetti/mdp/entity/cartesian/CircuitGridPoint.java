package com.gmrossetti.mdp.entity.cartesian;

import com.gmrossetti.mdp.pawn.IPawn;

public class CircuitGridPoint extends GridPoint {
    public enum GridPointType {
        OUTSIDE,  // Fuori mappa
        INSIDE,   // Dentro la mappa
    }
    public final GridPointType type;
    private IPawn occupiedBy;

    public CircuitGridPoint(int x, int y, GridPointType type) {
        super(x, y);
        this.type = type;
        this.occupiedBy = null;
    }

    public CircuitGridPoint(int x, int y, GridPointType type, IPawn occupiedBy) {
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
    public void setOccupiedBy(IPawn occupiedBy) {
        this.occupiedBy = occupiedBy;
    }
    public boolean isWalkable() {
        return this.type != GridPointType.OUTSIDE;
    }
}
