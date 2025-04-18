package com.gmrossetti.mdp.formulauno.circuit;

import com.gmrossetti.mdp.formulauno.circuit.tile.ITile;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.Waypoint;
import com.gmrossetti.mdp.formulauno.parser.CircuitParser;

import java.io.IOException;
import java.util.List;

public final class CircuitFactory {
    public static ICircuit buildCircuit(String circuitName){
        try {
            final ITile[][] grid = CircuitParser.parseImageToGrid(circuitName);
            final List<Waypoint> waypoints = CircuitParser.parseWaypointsJson(circuitName);

            return new Circuit(grid, waypoints);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
