package com.gmrossetti.mdp.strategy;

import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.driver.MoveCandidate;
import com.gmrossetti.mdp.entity.cartesian.GridPoint;
import com.gmrossetti.mdp.entity.waypoint.Waypoint;

import java.util.Comparator;
import java.util.List;

public class BasicStrategy extends Strategy {
    private final double maxVelocity;

    public BasicStrategy(StrategyParameters strategyParameters) {
        this.maxVelocity = StrategyParametersScaler.getMinVelocity(strategyParameters);
    }

    @Override
    public CarDriver.Move chooseBestMove(CarDriver carDriver, Circuit circuit) {
        final Waypoint currentWaypoint = carDriver.waypointTarget;

        if (!currentWaypoint.hasPrevious()) {
            throw new IllegalStateException("CarDriver must be initialized with the second waypoint");
        }

        final GridPoint target = currentWaypoint.getCenter();

        final List<MoveCandidate> filteredValidMoves = filterValidMoves(generateMoveCandidates(carDriver.getMovesPoints(),
                carDriver.getCar().getPosition(),target,getMedian(carDriver.waypointTarget)), carDriver, circuit);

        if(filteredValidMoves.isEmpty()){
            return CarDriver.Move.BL;
        }

        if (carDriver.getCar().getVelocityModule() > maxVelocity){
            return filteredValidMoves.stream()
                    .min(Comparator.comparingDouble(MoveCandidate::getDistanceToCurrent))
                    .orElseThrow().getMove();
        }

        return filteredValidMoves.stream()
                .min(Comparator.comparingDouble(MoveCandidate::getDistanceToTarget))
                .orElseThrow().getMove();
    }
}
