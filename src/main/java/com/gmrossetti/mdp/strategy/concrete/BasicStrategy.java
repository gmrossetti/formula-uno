package com.gmrossetti.mdp.strategy.concrete;

import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.driver.IDriver;
import com.gmrossetti.mdp.driver.move.Move;
import com.gmrossetti.mdp.driver.move.MoveCandidate;
import com.gmrossetti.mdp.cartesian.GridPoint;
import com.gmrossetti.mdp.circuit.waypoint.Waypoint;
import com.gmrossetti.mdp.strategy.StrategyParameters;
import com.gmrossetti.mdp.strategy.StrategyParametersScaler;

import java.util.Comparator;
import java.util.List;

public class BasicStrategy extends Strategy {
    private final double maxVelocity;

    public BasicStrategy(StrategyParameters strategyParameters) {
        this.maxVelocity = StrategyParametersScaler.getMinVelocity(strategyParameters);
    }

    @Override
    public Move chooseBestMove(IDriver driver, ICircuit circuit) {
        final Waypoint currentWaypoint = driver.getWaypointTarget();

        if (!currentWaypoint.hasPrevious()) {
            throw new IllegalStateException("CarDriver must be initialized with the second waypoint");
        }

        final GridPoint target = currentWaypoint.getCenter();

        final List<MoveCandidate> filteredValidMoves = filterValidMoves(generateMoveCandidates(driver.getMovesPoints(),
                driver.getCar().getPosition(),target,getMedian(driver.getWaypointTarget())), driver, circuit);

        if(filteredValidMoves.isEmpty()){
            return Move.BL;
        }

        if (driver.getCar().getVelocityModule() > maxVelocity){
            return filteredValidMoves.stream()
                    .min(Comparator.comparingDouble(MoveCandidate::distanceToCurrent))
                    .orElseThrow().move();
        }

        return filteredValidMoves.stream()
                .min(Comparator.comparingDouble(MoveCandidate::distanceToTarget))
                .orElseThrow().move();
    }
}
