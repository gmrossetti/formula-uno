package com.gmrossetti.mdp.formulauno.driver;

import com.gmrossetti.mdp.formulauno.cartesian.GridLine;
import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.Waypoint;
import com.gmrossetti.mdp.formulauno.driver.move.Move;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;

import java.util.Map;

public interface IDriver {
    IPawn getCar();
    boolean hasActiveWaypoint();
    Waypoint getWaypointTarget();
    GridLine makeMove();
    Map<Move, GridPoint> getMovesPoints();
}