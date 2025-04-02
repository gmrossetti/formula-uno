package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.cartesian.GridLine;
import com.gmrossetti.mdp.cartesian.GridPoint;
import com.gmrossetti.mdp.circuit.ITile;

import java.util.List;

public class DriverMoveValidator {
    public static boolean isMoveValid(GridLine driverTrace, ICircuit circuit) {
        if (driverTrace.isDegenerate()) {
            return true; // Mossa nulla, nessun effetto
        }

        if(circuit.getTile(driverTrace.getEnd()).isOccupied()) return false;

        List<GridPoint> intersectionPoints = driverTrace.getNearestGridPointsOnIntersections();
        List<ITile> trackIntersections = circuit.getTile(intersectionPoints);

        for (ITile tile:
                trackIntersections) {
            if(!tile.isOnTrack())
                return false;
        }

        return true;
    }
}
