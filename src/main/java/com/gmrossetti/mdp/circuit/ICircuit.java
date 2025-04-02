package com.gmrossetti.mdp.circuit;

import com.gmrossetti.mdp.cartesian.GridPoint;
import com.gmrossetti.mdp.circuit.waypoint.Waypoint;

import java.util.List;

public interface ICircuit {
    List<Waypoint> getWaypoints();
    ITile getTile(int x, int y);
    ITile getTile(GridPoint gp);
    List<ITile> getTile(List<GridPoint> gps);
    ITile getRaceStartPoint();
    int getGridWidth();
    int getGridHeight();
    Waypoint getWaypointsHead();
}
