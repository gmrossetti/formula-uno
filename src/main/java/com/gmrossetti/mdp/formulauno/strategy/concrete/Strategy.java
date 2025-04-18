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

/**
 * Abstract class representing a strategy for choosing the best move for a driver on a circuit.
 * This class provides common utility methods for calculating trajectory deviation, filtering valid moves,
 * and generating move candidates.
 */
abstract class Strategy implements IStrategy {
    /**
     * Calculates the trajectory deviation between the car's current position and a target point,
     * relative to a pivot point.
     *
     * @param car   The car (pawn) for which to calculate the trajectory deviation.
     * @param target The target point to which the car is moving.
     * @param pivot  The pivot point used for calculating the deviation.
     * @return The calculated trajectory deviation in degrees.
     */
    protected static double calculateTrajectoryDeviation(IPawn car, GridPoint target, GridPoint pivot) {
        GridLine toTarget = new GridLine(car.getPosition(), target);
        GridLine toPivot = new GridLine(car.getPosition(), pivot);

        if (toTarget.isDegenerate() || toPivot.isDegenerate()) {
            return 0;
        }

        return Math.abs(toTarget.getSlopeCoefficientToDegrees() - toPivot.getSlopeCoefficientToDegrees());
    }

    /**
     * Calculates the median point between two waypoints.
     *
     * @param currentWaypoint The current waypoint.
     * @return The median point between the current and previous waypoints.
     */
    protected static Point getMedian(Waypoint currentWaypoint) {
        Waypoint previousWaypoint = currentWaypoint.getPrevious();
        return new GridLine(previousWaypoint.getCenter(), currentWaypoint.getCenter()).getMedianPoint();
    }

    /**
     * Filters the list of move candidates to include only valid moves based on the driver's position
     * and the circuit's validation rules.
     *
     * @param moveCandidates The list of move candidates to filter.
     * @param driver         The driver for whom to filter the moves.
     * @param circuit        The circuit on which the driver is racing.
     * @return A filtered list of valid move candidates.
     */
    protected static List<MoveCandidate> filterValidMoves(List<MoveCandidate> moveCandidates, IDriver driver, ICircuit circuit) {
        return moveCandidates.stream()
                .filter(mc -> !(driver.getPawn().isStationary() && driver.getPawn().getPosition().equals(mc.movePoint())))
                .filter(mc -> DriverMoveValidator.isMoveValid(new GridLine(driver.getPawn().getPosition(), mc.movePoint()), circuit))
                .toList();
    }

    /**
     * Generates a list of move candidates based on the provided moves and their corresponding grid points.
     *
     * @param movesPoints  A map of moves and their corresponding grid points.
     * @param currentPos   The current position of the driver.
     * @param target       The target point to which the driver is moving.
     * @param median       The median point used for calculating distances.
     * @return A list of move candidates generated from the provided moves and grid points.
     */
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
