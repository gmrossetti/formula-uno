package com.gmrossetti.mdp.formulauno.strategy;

/**
 * Class representing the parameters for a strategy in the Formula Uno game.
 * This class encapsulates the normalized values for various strategy parameters
 * and provides methods to scale them to their respective ranges.
 */
public record StrategyParameters(double normalizedMinVelocity, double normalizedDeviationThreshold,
                                 double normalizedBrakeDistance, double normalizedAccelerateDistance) {


    /**
     * Constructs a new instance of StrategyParameters with normalized values.
     *
     * @param normalizedMinVelocity       The normalized minimum velocity (0 to 1).
     * @param normalizedDeviationThreshold The normalized deviation threshold (0 to 1).
     * @param normalizedBrakeDistance     The normalized brake distance (0 to 1).
     * @param normalizedAccelerateDistance The normalized accelerate distance (0 to 1).
     */
    public StrategyParameters {
        if (normalizedMinVelocity < 0 || normalizedMinVelocity > 1 ||
                normalizedDeviationThreshold < 0 || normalizedDeviationThreshold > 1 ||
                normalizedBrakeDistance < 0 || normalizedBrakeDistance > 1 ||
                normalizedAccelerateDistance < 0 || normalizedAccelerateDistance > 1) {
            throw new IllegalArgumentException("All normalized values must be between 0 and 1");
        }
    }

    public static final double MIN_VELOCITY_A = 1.0;
    public static final double MIN_VELOCITY_B = 3.0;

    public static final double DEVIATION_THRESHOLD_A = 10.0;
    public static final double DEVIATION_THRESHOLD_B = 80.0;

    public static final double BRAKE_DISTANCE_A = 0;
    public static final double BRAKE_DISTANCE_B = 4;

    public static final double ACCELERATE_DISTANCE_A = 2.0;
    public static final double ACCELERATE_DISTANCE_B = 8.0;

    /**
     * Returns the scaled minimum velocity based on the normalized value.
     *
     * @return The scaled minimum velocity.
     */
    public double getScaledMinVelocity() {
        return scale(normalizedMinVelocity, MIN_VELOCITY_A, MIN_VELOCITY_B);
    }

    /**
     * Returns the scaled deviation threshold based on the normalized value.
     *
     * @return The scaled deviation threshold.
     */
    public double getScaledDeviationThreshold() {
        return scale(normalizedDeviationThreshold,DEVIATION_THRESHOLD_A,DEVIATION_THRESHOLD_B);
    }

    /**
     * Returns the scaled brake distance based on the normalized value.
     *
     * @return The scaled brake distance.
     */
    public double getScaledBrakeDistance() {
        return scale(normalizedBrakeDistance, BRAKE_DISTANCE_A, BRAKE_DISTANCE_B);
    }

    /**
     * Returns the scaled accelerate distance based on the normalized value.
     *
     * @return The scaled accelerate distance.
     */
    public double getScaledAccelerateDistance() {
        return scale(normalizedAccelerateDistance, ACCELERATE_DISTANCE_A, ACCELERATE_DISTANCE_B);
    }

    /**
     * Scales a normalized value to a specified range.
     *
     * @param value      The normalized value (0 to 1).
     * @param rangeStart The start of the range.
     * @param rangeEnd   The end of the range.
     * @return The scaled value.
     */
    private static double scale(double value, double rangeStart, double rangeEnd) {
        final double min = Math.min(rangeStart, rangeEnd);
        final double max = Math.max(rangeStart, rangeEnd);

        return min + value * (max - min);
    }
}