package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.entity.cartesian.CircuitGridPoint;
import com.gmrossetti.mdp.entity.cartesian.GridLine;
import com.gmrossetti.mdp.entity.cartesian.GridPoint;

import java.util.ArrayList;
import java.util.List;

public class DriverMoveValidator {
    public static boolean isMoveValid(GridLine driverTrace, Circuit circuit) {
        if (driverTrace.isDegenerate()) {
            return true; // Mossa nulla, nessun effetto
        }

        List<GridPoint> intersectionPoints = driverTrace.getNearestGridPointsOnIntersections();
        List<CircuitGridPoint> trackIntersections = circuit.toCircuitGridPoint(intersectionPoints);

        List<CircuitGridPoint.GridPointType> uniquePathSequence = extractUniqueGridPointTypes(trackIntersections);

        return determineMoveResult(uniquePathSequence);
    }

    private static List<CircuitGridPoint.GridPointType> extractUniqueGridPointTypes(List<CircuitGridPoint> intersections) {
        List<CircuitGridPoint.GridPointType> uniqueSequence = new ArrayList<>();
        CircuitGridPoint.GridPointType lastAdded = null;

        for (CircuitGridPoint point : intersections) {
            if (point.type != lastAdded) {
                uniqueSequence.add(point.type);
                lastAdded = point.type;
            }
        }

        return uniqueSequence;
    }

    private static boolean determineMoveResult(List<CircuitGridPoint.GridPointType> trailTypesEncountered) {
        if (trailTypesEncountered.isEmpty()) {
            throw new IllegalArgumentException("Trail list cannot be empty.");
        }

        CircuitGridPoint.GridPointType firstPoint = trailTypesEncountered.get(0);

        if (firstPoint != CircuitGridPoint.GridPointType.START && firstPoint != CircuitGridPoint.GridPointType.INSIDE) {
            throw new IllegalStateException("Trail can start only on race start line or inside track points.");
        }

        return evaluateTrailRecursively(trailTypesEncountered, 0);
    }

    private static boolean evaluateTrailRecursively(List<CircuitGridPoint.GridPointType> trail, int index) {
        if (index > trail.size() - 2) {
            return true;
        }

        CircuitGridPoint.GridPointType current = trail.get(index);
        CircuitGridPoint.GridPointType next = trail.get(index + 1);

        switch (current) {
            case START:
                if (next == CircuitGridPoint.GridPointType.OUTSIDE) {
                    return false;
                }
                return evaluateTrailRecursively(trail, index + 1);

            case INSIDE:
                if (next == CircuitGridPoint.GridPointType.START) {
                    return evaluateTrailRecursively(trail, index + 1);
                }
                if (next == CircuitGridPoint.GridPointType.OUTSIDE) {
                    return false;
                }
                if (next == CircuitGridPoint.GridPointType.END) {
                    return true;
                }
                break;

            default:
        }

        throw new IllegalStateException("Unexpected grid point encountered: " + current);
    }
}
