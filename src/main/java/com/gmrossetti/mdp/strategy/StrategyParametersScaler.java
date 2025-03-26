package com.gmrossetti.mdp.strategy;

public class StrategyParametersScaler {
    public static final double MIN_VELOCITY_A = 1.0;
    public static final double MIN_VELOCITY_B = 3.0;

    public static final double DEVIATION_THRESHOLD_A = 10.0;
    public static final double DEVIATION_THRESHOLD_B = 80.0;

    public static final double BRAKE_DISTANCE_A = 0;
    public static final double BRAKE_DISTANCE_B = 4;

    public static final double ACCELERATE_DISTANCE_A = 2.0;
    public static final double ACCELERATE_DISTANCE_B = 8.0;

    public static double getMinVelocity(StrategyParameters strategyParameters) {
        return scale(strategyParameters.getMinVelocity(), MIN_VELOCITY_A, MIN_VELOCITY_B);
    }

    public static double getDeviationThreshold(StrategyParameters strategyParameters) {
        return scale(strategyParameters.getDeviationThreshold(),DEVIATION_THRESHOLD_A,DEVIATION_THRESHOLD_B);
    }

    public static double getBrakeDistance(StrategyParameters strategyParameters) {
        return scale(strategyParameters.getBrakeDistance(), BRAKE_DISTANCE_A, BRAKE_DISTANCE_B);
    }

    public static double getAccelerateDistance(StrategyParameters strategyParameters) {
        return scale(strategyParameters.getAccelerateDistance(), ACCELERATE_DISTANCE_A, ACCELERATE_DISTANCE_B);
    }

    private static double scale(double value, double rangeStart, double rangeEnd) {
        final double min = Math.min(rangeStart, rangeEnd);
        final double max = Math.max(rangeStart, rangeEnd);

        return min + value * (max - min);
    }
}
