package com.gmrossetti.mdp.strategy;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.core.DriverMoveValidator;
import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.driver.MoveCandidate;
import com.gmrossetti.mdp.entity.cartesian.GridLine;
import com.gmrossetti.mdp.entity.cartesian.GridPoint;
import com.gmrossetti.mdp.entity.cartesian.Point;
import com.gmrossetti.mdp.entity.waypoint.Waypoint;

import java.util.*;

class AdvancedStrategy implements IStrategy {

    private enum SpeedAction {
        BRAKE, NEUTRAL, GAS
    }

    private final double minVelocity;
    private final double deviationThreshold;
    private final double brakeDistance;
    private final double accelerateDistance;

    public AdvancedStrategy(StrategyParameters strategyParameters) {
        this.minVelocity = StrategyParametersScaler.getMinVelocity(strategyParameters);
        this.deviationThreshold = StrategyParametersScaler.getDeviationThreshold(strategyParameters);
        this.brakeDistance = StrategyParametersScaler.getBrakeDistance(strategyParameters);
        this.accelerateDistance = StrategyParametersScaler.getAccelerateDistance(strategyParameters);
    }

    @Override
    public CarDriver.Move chooseBestMove(CarDriver carDriver, Circuit circuit) {
        final Map<CarDriver.Move, GridPoint> movesPoints = carDriver.getMovesPoints();
        final GridPoint currentPosition = carDriver.getCar().getPosition();
        final Waypoint currentWaypoint = carDriver.waypointTarget;

        if (!currentWaypoint.hasPrevious()) {
            throw new IllegalStateException("CarDriver must be initialized with the second waypoint");
        }

        final Waypoint previousWaypoint = currentWaypoint.getPrevious();
        final Point medianPoint = new GridLine(previousWaypoint.getCenter(), currentWaypoint.getCenter()).getMedianPoint();

        List<MoveCandidate> moveCandidates = generateMoveCandidates(movesPoints, currentPosition, currentWaypoint.getCenter(), medianPoint);
        moveCandidates.sort(Comparator.comparingDouble(MoveCandidate::getDistanceToCurrent));

        List<MoveCandidate> brakeCandidates = moveCandidates.subList(0, 3);
        List<MoveCandidate> neutralCandidates = moveCandidates.subList(3, 6);
        List<MoveCandidate> gasCandidates = moveCandidates.subList(6, 9);

        SpeedAction speedAction = determineSpeedAction(carDriver);

        return selectBestMove(speedAction, brakeCandidates, neutralCandidates, gasCandidates, carDriver, circuit);
    }

    private static List<MoveCandidate> generateMoveCandidates(Map<CarDriver.Move, GridPoint> movesPoints,
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

    private SpeedAction determineSpeedAction(CarDriver carDriver) {
        Car car = carDriver.getCar();
        GridPoint pivot = car.getPivot();
        GridPoint target = carDriver.waypointTarget.getCenter();
        double deviation = calculateTrajectoryDeviation(car, target, pivot);
        double distanceToTarget = pivot.distanceTo(target);
        double medianDistance = getMedian(carDriver).distanceTo(target);

        if ((distanceToTarget < medianDistance - brakeDistance || deviation > deviationThreshold) && car.getVelocityModule() > minVelocity) {
            return SpeedAction.BRAKE;
        }
        if (distanceToTarget > medianDistance + accelerateDistance && car.getVelocityModule() < 2) {
            return SpeedAction.GAS;
        }
        return SpeedAction.NEUTRAL;
    }

    private static double calculateTrajectoryDeviation(Car car, GridPoint target, GridPoint pivot) {
        GridLine toTarget = new GridLine(car.getPosition(), target);
        GridLine toPivot = new GridLine(car.getPosition(), pivot);

        if (toTarget.isDegenerate() || toPivot.isDegenerate()) {
            return 0;
        }

        return Math.abs(toTarget.getSlopeCoefficientToDegrees() - toPivot.getSlopeCoefficientToDegrees());
    }

    private static CarDriver.Move selectBestMove(SpeedAction speedAction, List<MoveCandidate> brakes,
                                          List<MoveCandidate> neutrals, List<MoveCandidate> gas,
                                          CarDriver carDriver, Circuit circuit) {

        List<List<MoveCandidate>> prioritizedMoves = switch (speedAction) {
            case BRAKE -> List.of(brakes, neutrals, gas);
            case NEUTRAL -> List.of(neutrals, brakes, gas);
            case GAS -> List.of(gas, neutrals, brakes);
        };

        for (List<MoveCandidate> moveList : prioritizedMoves) {
            List<MoveCandidate> filteredMoves = filterValidMoves(moveList, carDriver, circuit);
            if (!filteredMoves.isEmpty()) {
                return filteredMoves.stream()
                        .min(Comparator.comparingDouble(MoveCandidate::getDistanceToTarget))
                        .orElseThrow()
                        .getMove();
            }
        }

        return CarDriver.Move.BL;
    }

    private static Point getMedian(CarDriver carDriver) {
        Waypoint currentWaypoint = carDriver.waypointTarget;
        Waypoint previousWaypoint = currentWaypoint.getPrevious();
        return new GridLine(previousWaypoint.getCenter(), currentWaypoint.getCenter()).getMedianPoint();
    }

    private static List<MoveCandidate> filterValidMoves(List<MoveCandidate> moveCandidates, CarDriver carDriver, Circuit circuit) {
        return moveCandidates.stream()
                .filter(mc -> !(carDriver.getCar().isStationary() && carDriver.getCar().getPosition().equals(mc.getMovePoint())))
                .filter(mc -> DriverMoveValidator.isMoveValid(new GridLine(carDriver.getCar().getPosition(), mc.getMovePoint()), circuit))
                .toList();
    }
}
