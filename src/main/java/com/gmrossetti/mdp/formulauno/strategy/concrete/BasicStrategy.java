package com.gmrossetti.mdp.formulauno.strategy.concrete;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.driver.IDriver;
import com.gmrossetti.mdp.formulauno.driver.move.Move;
import com.gmrossetti.mdp.formulauno.driver.move.MoveCandidate;
import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.Waypoint;
import com.gmrossetti.mdp.formulauno.strategy.StrategyParameters;

import java.util.Comparator;
import java.util.List;

public class BasicStrategy extends Strategy {
    private final double maxVelocity;

    public BasicStrategy(final StrategyParameters strategyParameters) {
        this.maxVelocity = strategyParameters.getScaledMinVelocity();
    }

    @Override
    public Move chooseBestMove(IDriver driver, ICircuit circuit) {
        final Waypoint currentWaypoint = driver.getWaypointTarget();

        if (!currentWaypoint.hasPrevious()) {
            throw new IllegalStateException("CarDriver must be initialized with the second waypoint");
        }

        final GridPoint target = currentWaypoint.getCenter();

        final List<MoveCandidate> filteredValidMoves = filterValidMoves(generateMoveCandidates(driver.getMovesPoints(),
                driver.getPawn().getPosition(),target,getMedian(driver.getWaypointTarget())), driver, circuit);

        if(filteredValidMoves.isEmpty()){
            return Move.BL;
        }

        if (driver.getPawn().getVelocityModule() > maxVelocity){
            return filteredValidMoves.stream()
                    .min(Comparator.comparingDouble(MoveCandidate::distanceToCurrent))
                    .orElseThrow().move();
        }

        return filteredValidMoves.stream()
                .min(Comparator.comparingDouble(MoveCandidate::distanceToTarget))
                .orElseThrow().move();
    }
}
