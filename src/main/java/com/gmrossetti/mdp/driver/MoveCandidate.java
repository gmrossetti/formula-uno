package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.cartesian.GridPoint;

// TODO: refactor in Record class
public class MoveCandidate {
    private final GridPoint movePoint;

    public Move getMove() {
        return move;
    }

    private final Move move;
    private final double distanceToCurrent;
    private final double distanceToTarget;
    private final double distanceToMedian;

    public MoveCandidate(Move move, GridPoint movePoint, double distanceToCurrent, double distanceToTarget, double distanceToMedian) {
        this.move = move;
        this.movePoint = movePoint;
        this.distanceToCurrent = distanceToCurrent;
        this.distanceToTarget = distanceToTarget;
        this.distanceToMedian = distanceToMedian;
    }

    public GridPoint getMovePoint() {
        return movePoint;
    }

    public double getDistanceToCurrent() {
        return distanceToCurrent;
    }

    public double getDistanceToTarget() {
        return distanceToTarget;
    }

    public double getDistanceToMedian() {
        return distanceToMedian;
    }

    @Override
    public String toString(){
        return "{ move: " + move.name() + ", movePoint: " + movePoint + ", distanceToCurrent: " + distanceToCurrent
                + ", distanceToTarget: " + distanceToTarget + ", distanceToMedian: " + distanceToMedian + " }";
    }
}
