package com.gmrossetti.mdp.circuit;

import com.gmrossetti.mdp.cartesian.CircuitGridPoint;
import com.gmrossetti.mdp.cartesian.GridPoint;
import com.gmrossetti.mdp.circuit.waypoint.Waypoint;

import java.util.List;

public interface ICircuit {
    List<Waypoint> getWaypoints();
    CircuitGridPoint getGridPoint(int x, int y);
    CircuitGridPoint getGridPoint(GridPoint gp);
    CircuitGridPoint getRaceStartPoint();
    int getGridWidth();
    int getGridHeight();
    List<CircuitGridPoint> toCircuitGridPoint(List<GridPoint> points);
    CircuitGridPoint toCircuitGridPoint(GridPoint point);
    Waypoint getWaypointsHead();
}
