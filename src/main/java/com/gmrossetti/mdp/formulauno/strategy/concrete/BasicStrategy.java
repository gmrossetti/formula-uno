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

/**
 * BasicStrategy is a concrete implementation of the Strategy class.
 * It provides a basic strategy for choosing the best move for a driver on a circuit.
 * The strategy considers the driver's current velocity and the target waypoint to determine the best move.
 */
public class BasicStrategy extends Strategy {
    private final double minVelocity;

    /**
     * Constructor for BasicStrategy.
     *
     * @param strategyParameters The parameters required to configure the strategy.
     */
    public BasicStrategy(final StrategyParameters strategyParameters) {
        this.minVelocity = strategyParameters.getScaledMinVelocity();
    }

    /**
     * Chooses the best move for the given driver on the specified circuit.
     * The strategy considers the driver's current velocity and the target waypoint to determine the best move.
     *
     * @param driver  The driver for whom to choose the best move.
     * @param circuit The circuit on which the driver is racing.
     * @return The best move for the driver on the circuit.
     */
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

        if (driver.getPawn().getVelocityModule() > minVelocity){
            return filteredValidMoves.stream()
                    .min(Comparator.comparingDouble(MoveCandidate::distanceToCurrent))
                    .orElseThrow().move();
        }

        return filteredValidMoves.stream()
                .min(Comparator.comparingDouble(MoveCandidate::distanceToTarget))
                .orElseThrow().move();
    }
}
