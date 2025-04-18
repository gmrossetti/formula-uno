package com.gmrossetti.mdp.formulauno.circuit;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.circuit.tile.ITile;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.Waypoint;

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
