package com.gmrossetti.mdp.formulauno.strategy;

public record StrategyParameters(double normalizedMinVelocity, double normalizedDeviationThreshold,
                                 double normalizedBrakeDistance, double normalizedAccelerateDistance) {
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

    public double getScaledMinVelocity() {
        return scale(normalizedMinVelocity, MIN_VELOCITY_A, MIN_VELOCITY_B);
    }

    public double getScaledDeviationThreshold() {
        return scale(normalizedDeviationThreshold,DEVIATION_THRESHOLD_A,DEVIATION_THRESHOLD_B);
    }

    public double getScaledBrakeDistance() {
        return scale(normalizedBrakeDistance, BRAKE_DISTANCE_A, BRAKE_DISTANCE_B);
    }

    public double getScaledAccelerateDistance() {
        return scale(normalizedAccelerateDistance, ACCELERATE_DISTANCE_A, ACCELERATE_DISTANCE_B);
    }

    private static double scale(double value, double rangeStart, double rangeEnd) {
        final double min = Math.min(rangeStart, rangeEnd);
        final double max = Math.max(rangeStart, rangeEnd);

        return min + value * (max - min);
    }
}