package com.gmrossetti.mdp.formulauno.strategy.concrete;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.driver.IDriver;
import com.gmrossetti.mdp.formulauno.driver.move.Move;
import com.gmrossetti.mdp.formulauno.cartesian.Point;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;
import com.gmrossetti.mdp.formulauno.driver.move.MoveCandidate;
import com.gmrossetti.mdp.formulauno.cartesian.GridLine;
import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.Waypoint;
import com.gmrossetti.mdp.formulauno.strategy.StrategyParameters;

import java.util.*;

/**
 * AdvancedStrategy is a concrete implementation of the Strategy class.
 * It provides an advanced strategy for choosing the best move for a driver on a circuit.
 * The strategy considers the driver's current velocity, trajectory deviation, and distance to the target waypoint
 * to determine the best move.
 */
public class AdvancedStrategy extends Strategy {
    /**
     * Enum representing the possible speed actions for the driver.
     */
    private enum SpeedAction {
        BRAKE, NEUTRAL, GAS
    }

    private final double minVelocity;
    private final double deviationThreshold;
    private final double brakeDistance;
    private final double accelerateDistance;

    /**
     * Constructor for AdvancedStrategy.
     *
     * @param strategyParameters The parameters required to configure the strategy.
     */
    public AdvancedStrategy(StrategyParameters strategyParameters) {
        this.minVelocity = strategyParameters.getScaledMinVelocity();
        this.deviationThreshold = strategyParameters.getScaledDeviationThreshold();
        this.brakeDistance = strategyParameters.getScaledBrakeDistance();
        this.accelerateDistance = strategyParameters.getScaledAccelerateDistance();
    }

    /**
     * Chooses the best move for the given driver on the specified circuit.
     * The strategy considers the driver's current velocity, trajectory deviation, and distance to the target waypoint
     * to determine the best move.
     *
     * @param driver The driver for whom to choose the best move.
     * @param circuit The circuit on which the driver is racing.
     * @return The best move for the driver on the circuit.
     */
    @Override
    public Move chooseBestMove(IDriver driver, ICircuit circuit) {
        final Map<Move, GridPoint> movesPoints = driver.getMovesPoints();
        final GridPoint currentPosition = driver.getPawn().getPosition();
        final Waypoint currentWaypoint = driver.getWaypointTarget();

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

        SpeedAction speedAction = determineSpeedAction(driver);

        return selectBestMove(speedAction, brakeCandidates, neutralCandidates, gasCandidates, driver, circuit);
    }

    /**
     * Determines the speed action (BRAKE, NEUTRAL, GAS) based on the driver's current state and trajectory.
     *
     * @param driver The driver for whom to determine the speed action.
     * @return The determined speed action.
     */
    private SpeedAction determineSpeedAction(IDriver driver) {
        IPawn car = driver.getPawn();
        GridPoint pivot = car.getPivot();
        GridPoint target = driver.getWaypointTarget().getCenter();
        double deviation = calculateTrajectoryDeviation(car, target, pivot);
        double distanceToTarget = pivot.distanceTo(target);
        double medianDistance = getMedian(driver.getWaypointTarget()).distanceTo(target);

        if ((distanceToTarget < medianDistance - brakeDistance || deviation > deviationThreshold) && car.getVelocityModule() > minVelocity) {
            return SpeedAction.BRAKE;
        }
        if (distanceToTarget > medianDistance + accelerateDistance && car.getVelocityModule() < 2) {
            return SpeedAction.GAS;
        }
        return SpeedAction.NEUTRAL;
    }

    /**
     * Selects the best move based on the speed action and the available move candidates.
     *
     * @param speedAction The determined speed action (BRAKE, NEUTRAL, GAS).
     * @param brakes      The list of brake move candidates.
     * @param neutrals    The list of neutral move candidates.
     * @param gas         The list of gas move candidates.
     * @param IDriver     The driver for whom to select the best move.
     * @param circuit     The circuit on which the driver is racing.
     * @return The selected best move for the driver.
     */
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

        // If no valid moves are found, return a default move (e.g., BL)
        return Move.BL;
    }
}
