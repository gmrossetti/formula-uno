package com.gmrossetti.mdp.formulauno.driver.move;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.cartesian.GridLine;
import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.circuit.tile.ITile;

import java.util.List;

/**
 * DriverMoveValidator class that validates the moves of a driver.
 * It checks if the move is valid based on the circuit and the driver's trace.
 */
public class DriverMoveValidator {
    /**
     * Validates the move of a driver.
     *
     * @param driverTrace The trace of the driver.
     * @param circuit     The circuit the driver is racing on.
     * @return True if the move is valid, false otherwise.
     */
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
