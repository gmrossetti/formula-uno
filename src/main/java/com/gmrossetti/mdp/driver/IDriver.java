package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.cartesian.GridLine;
import com.gmrossetti.mdp.cartesian.GridPoint;
import com.gmrossetti.mdp.pawn.IPawn;

import java.util.Map;

public interface IDriver {
    IPawn getCar();
    boolean hasActiveWaypoint();
    GridLine makeMove();
    Map<CarDriver.Move, GridPoint> getMovesPoints();
}