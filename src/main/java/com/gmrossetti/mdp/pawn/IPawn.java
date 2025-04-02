package com.gmrossetti.mdp.pawn;

import com.gmrossetti.mdp.entity.cartesian.GridPoint;

import java.util.List;

public interface IPawn {
    GridPoint getPosition();
    List<GridPoint> getTrail();
    boolean isStationary();
    GridPoint getVelocityVector();
    double getVelocityModule();
    void move(GridPoint position);
    GridPoint getPivot();
}
