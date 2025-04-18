package com.gmrossetti.mdp.formulauno.driver.move;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;

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
