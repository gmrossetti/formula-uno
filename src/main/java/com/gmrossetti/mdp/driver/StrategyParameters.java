package com.gmrossetti.mdp.driver;

public class StrategyParameters {
    private final double minVelocity;
    private final double deviationThreshold;
    private final double brakeDistance;
    private final double accelerateDistance;

    public StrategyParameters(double minVelocity, double deviationThreshold, double brakeDistance, double accelerateDistance) {
        this.minVelocity = minVelocity;
        this.deviationThreshold = deviationThreshold;
        this.brakeDistance = brakeDistance;
        this.accelerateDistance = accelerateDistance;
    }

    public double getMinVelocity() {
        return minVelocity;
    }

    public double getDeviationThreshold() {
        return deviationThreshold;
    }

    public double getBrakeDistance() {
        return brakeDistance;
    }

    public double getAccelerateDistance() {
        return accelerateDistance;
    }
}
