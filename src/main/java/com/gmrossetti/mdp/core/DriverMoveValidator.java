package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.entity.CircuitGridPoint;
import com.gmrossetti.mdp.entity.GridLine;
import com.gmrossetti.mdp.entity.GridPoint;

import java.util.ArrayList;
import java.util.List;

public class DriverMoveValidator {
    public enum MoveResult {
        OK, OFFTRACK, FINISH, CHEAT
    }

    public static MoveResult evaluateMove(GridLine driverPath, Circuit circuit) {
        if (driverPath.isDegenerate()) {
            return MoveResult.OK; // Mossa nulla, nessun effetto
        }

        List<GridPoint> intersectionPoints = driverPath.getNearestGridPointsOnIntersections();
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

    private static MoveResult determineMoveResult(List<CircuitGridPoint.GridPointType> trailTypesEncountered) {
        if (trailTypesEncountered.isEmpty()) {
            throw new IllegalArgumentException("Trail list cannot be empty.");
        }

        CircuitGridPoint.GridPointType firstPoint = trailTypesEncountered.get(0);

        if (firstPoint != CircuitGridPoint.GridPointType.START && firstPoint != CircuitGridPoint.GridPointType.INSIDE) {
            throw new IllegalStateException("Trail can start only on race start line or inside track points.");
        }

        return evaluateTrailRecursively(trailTypesEncountered, 0);
    }

    private static MoveResult evaluateTrailRecursively(List<CircuitGridPoint.GridPointType> trail, int index) {
        if (index > trail.size() - 2) {
            return MoveResult.OK;
        }

        CircuitGridPoint.GridPointType current = trail.get(index);
        CircuitGridPoint.GridPointType next = trail.get(index + 1);

        switch (current) {
            case START:
                if (next == CircuitGridPoint.GridPointType.END) {
                    return MoveResult.CHEAT;
                }
                if (next == CircuitGridPoint.GridPointType.OUTSIDE) {
                    return MoveResult.OFFTRACK;
                }
                return evaluateTrailRecursively(trail, index + 1);

            case INSIDE:
                if (next == CircuitGridPoint.GridPointType.START) {
                    return evaluateTrailRecursively(trail, index + 1);
                }
                if (next == CircuitGridPoint.GridPointType.OUTSIDE) {
                    return MoveResult.OFFTRACK;
                }
                if (next == CircuitGridPoint.GridPointType.END) {
                    return MoveResult.FINISH;
                }
                break;

            default:
        }

        throw new IllegalStateException("Unexpected grid point encountered: " + current);
    }
}
