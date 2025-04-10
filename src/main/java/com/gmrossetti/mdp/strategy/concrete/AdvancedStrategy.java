package com.gmrossetti.mdp.strategy.concrete;

import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.driver.IDriver;
import com.gmrossetti.mdp.driver.Move;
import com.gmrossetti.mdp.pawn.IPawn;
import com.gmrossetti.mdp.driver.MoveCandidate;
import com.gmrossetti.mdp.cartesian.GridLine;
import com.gmrossetti.mdp.cartesian.GridPoint;
import com.gmrossetti.mdp.cartesian.Point;
import com.gmrossetti.mdp.circuit.waypoint.Waypoint;
import com.gmrossetti.mdp.strategy.StrategyParameters;
import com.gmrossetti.mdp.strategy.StrategyParametersScaler;

import java.util.*;

public class AdvancedStrategy extends Strategy {
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
    public Move chooseBestMove(IDriver IDriver, ICircuit circuit) {
        final Map<Move, GridPoint> movesPoints = IDriver.getMovesPoints();
        final GridPoint currentPosition = IDriver.getCar().getPosition();
        final Waypoint currentWaypoint = IDriver.getWaypointTarget();

        if (!currentWaypoint.hasPrevious()) {
            throw new IllegalStateException("IDriver must be initialized with the second waypoint");
        }

        final Waypoint previousWaypoint = currentWaypoint.getPrevious();
        final Point medianPoint = new GridLine(previousWaypoint.getCenter(), currentWaypoint.getCenter()).getMedianPoint();

        List<MoveCandidate> moveCandidates = generateMoveCandidates(movesPoints, currentPosition, currentWaypoint.getCenter(), medianPoint);
        moveCandidates.sort(Comparator.comparingDouble(MoveCandidate::distanceToCurrent));

        List<MoveCandidate> brakeCandidates = moveCandidates.subList(0, 3);
        List<MoveCandidate> neutralCandidates = moveCandidates.subList(3, 6);
        List<MoveCandidate> gasCandidates = moveCandidates.subList(6, 9);

        SpeedAction speedAction = determineSpeedAction(IDriver);

        return selectBestMove(speedAction, brakeCandidates, neutralCandidates, gasCandidates, IDriver, circuit);
    }



    private SpeedAction determineSpeedAction(IDriver IDriver) {
        IPawn car = IDriver.getCar();
        GridPoint pivot = car.getPivot();
        GridPoint target = IDriver.getWaypointTarget().getCenter();
        double deviation = calculateTrajectoryDeviation(car, target, pivot);
        double distanceToTarget = pivot.distanceTo(target);
        double medianDistance = getMedian(IDriver.getWaypointTarget()).distanceTo(target);

        if ((distanceToTarget < medianDistance - brakeDistance || deviation > deviationThreshold) && car.getVelocityModule() > minVelocity) {
            return SpeedAction.BRAKE;
        }
        if (distanceToTarget > medianDistance + accelerateDistance && car.getVelocityModule() < 2) {
            return SpeedAction.GAS;
        }
        return SpeedAction.NEUTRAL;
    }

    private static Move selectBestMove(SpeedAction speedAction, List<MoveCandidate> brakes,
                                          List<MoveCandidate> neutrals, List<MoveCandidate> gas,
                                          IDriver IDriver, ICircuit circuit) {

        List<List<MoveCandidate>> prioritizedMoves = switch (speedAction) {
            case BRAKE -> List.of(brakes, neutrals, gas);
            case NEUTRAL -> List.of(neutrals, brakes, gas);
            case GAS -> List.of(gas, neutrals, brakes);
        };

        for (List<MoveCandidate> moveList : prioritizedMoves) {
            List<MoveCandidate> filteredMoves = filterValidMoves(moveList, IDriver, circuit);
            if (!filteredMoves.isEmpty()) {
                return filteredMoves.stream()
                        .min(Comparator.comparingDouble(MoveCandidate::distanceToTarget))
                        .orElseThrow()
                        .move();
            }
        }

        return Move.BL;
    }
}
