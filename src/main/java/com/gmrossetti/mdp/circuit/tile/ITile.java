package com.gmrossetti.mdp.circuit.tile;

import com.gmrossetti.mdp.cartesian.GridPoint;
import com.gmrossetti.mdp.pawn.IPawn;

public interface ITile {
    GridPoint getGridPoint();
    boolean isOnTrack();
    IPawn getOccupiedBy();
    boolean isOccupied();
    void setOccupiedBy(IPawn occupiedBy);
}
