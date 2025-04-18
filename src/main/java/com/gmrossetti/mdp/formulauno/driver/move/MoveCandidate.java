package com.gmrossetti.mdp.formulauno.driver.move;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;

/**
 * MoveCandidate class that represents a candidate move for a driver.
 * It contains the move, the point of the move, and the distances to the current position,
 * target position, and median position.
 */
public record MoveCandidate (
        Move move,
        GridPoint movePoint,
        double distanceToCurrent,
        double distanceToTarget,
        double distanceToMedian) {
    @Override
    public String toString(){
        return "{ move: " + move.name() + ", movePoint: " + movePoint + ", distanceToCurrent: " + distanceToCurrent
                + ", distanceToTarget: " + distanceToTarget + ", distanceToMedian: " + distanceToMedian + " }";
    }
}
