package com.gmrossetti.mdp.actor;

import com.gmrossetti.mdp.entity.cartesian.CircuitGridPoint;
import com.gmrossetti.mdp.entity.waypoint.Waypoint;
import com.gmrossetti.mdp.parser.CircuitParser;

import java.io.IOException;
import java.util.List;

public final class CircuitFactory {
    public static ICircuit buildCircuit(String circuitName){
        try {
            final CircuitGridPoint[][] grid = CircuitParser.parseImageToGrid(circuitName);
            final List<Waypoint> waypoints = CircuitParser.parseWaypointsJson(circuitName);

            return new Circuit(grid, waypoints);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
