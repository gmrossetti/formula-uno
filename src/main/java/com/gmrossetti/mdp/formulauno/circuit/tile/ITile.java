package com.gmrossetti.mdp.formulauno.circuit.tile;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;

public interface ITile {
    GridPoint getGridPoint();
    boolean isOnTrack();
    IPawn getOccupiedBy();
    boolean isOccupied();
    void setOccupiedBy(IPawn occupiedBy);
}
