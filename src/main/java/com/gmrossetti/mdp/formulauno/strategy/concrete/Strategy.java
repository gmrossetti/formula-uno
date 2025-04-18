package com.gmrossetti.mdp.formulauno.strategy.concrete;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.driver.IDriver;
import com.gmrossetti.mdp.formulauno.driver.move.Move;
import com.gmrossetti.mdp.formulauno.driver.move.DriverMoveValidator;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;
import com.gmrossetti.mdp.formulauno.driver.move.MoveCandidate;
import com.gmrossetti.mdp.formulauno.cartesian.GridLine;
import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.cartesian.Point;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.Waypoint;
import com.gmrossetti.mdp.formulauno.strategy.IStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract class Strategy implements IStrategy {

    protected static double calculateTrajectoryDeviation(IPawn car, GridPoint target, GridPoint pivot) {
        GridLine toTarget = new GridLine(car.getPosition(), target);
        GridLine toPivot = new GridLine(car.getPosition(), pivot);

        if (toTarget.isDegenerate() || toPivot.isDegenerate()) {
            return 0;
        }

        return Math.abs(toTarget.getSlopeCoefficientToDegrees() - toPivot.getSlopeCoefficientToDegrees());
    }

    protected static Point getMedian(Waypoint currentWaypoint) {
        Waypoint previousWaypoint = currentWaypoint.getPrevious();
        return new GridLine(previousWaypoint.getCenter(), currentWaypoint.getCenter()).getMedianPoint();
    }

    protected static List<MoveCandidate> filterValidMoves(List<MoveCandidate> moveCandidates, IDriver driver, ICircuit circuit) {
        return moveCandidates.stream()
                .filter(mc -> !(driver.getPawn().isStationary() && driver.getPawn().getPosition().equals(mc.movePoint())))
                .filter(mc -> DriverMoveValidator.isMoveValid(new GridLine(driver.getPawn().getPosition(), mc.movePoint()), circuit))
                .toList();
    }

    protected static List<MoveCandidate> generateMoveCandidates(Map<Move, GridPoint> movesPoints,
                                                              GridPoint currentPos, GridPoint target, Point median) {
        List<MoveCandidate> moveCandidates = new ArrayList<>();

        for (var entry : movesPoints.entrySet()) {
            GridPoint gp = entry.getValue();
            moveCandidates.add(new MoveCandidate(entry.getKey(), gp,
                    gp.distanceTo(currentPos),
                    gp.distanceTo(target),
                    gp.distanceTo(median)));
        }

        return moveCandidates;
    }
}
