package com.gmrossetti.mdp.strategy.concrete;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.core.DriverMoveValidator;
import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.driver.MoveCandidate;
import com.gmrossetti.mdp.entity.cartesian.GridLine;
import com.gmrossetti.mdp.entity.cartesian.GridPoint;
import com.gmrossetti.mdp.entity.cartesian.Point;
import com.gmrossetti.mdp.entity.waypoint.Waypoint;
import com.gmrossetti.mdp.strategy.IStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract class Strategy implements IStrategy {

    protected static double calculateTrajectoryDeviation(Car car, GridPoint target, GridPoint pivot) {
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

    protected static List<MoveCandidate> filterValidMoves(List<MoveCandidate> moveCandidates, CarDriver carDriver, Circuit circuit) {
        return moveCandidates.stream()
                .filter(mc -> !(carDriver.getCar().isStationary() && carDriver.getCar().getPosition().equals(mc.getMovePoint())))
                .filter(mc -> DriverMoveValidator.isMoveValid(new GridLine(carDriver.getCar().getPosition(), mc.getMovePoint()), circuit))
                .toList();
    }

    protected static List<MoveCandidate> generateMoveCandidates(Map<CarDriver.Move, GridPoint> movesPoints,
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
