package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.cartesian.GridLine;
import com.gmrossetti.mdp.cartesian.GridPoint;
import com.gmrossetti.mdp.circuit.waypoint.Waypoint;
import com.gmrossetti.mdp.pawn.IPawn;

import java.util.Map;

public interface IDriver {
    IPawn getCar();
    boolean hasActiveWaypoint();
    Waypoint getWaypointTarget();
    GridLine makeMove();
    Map<Move, GridPoint> getMovesPoints();
}