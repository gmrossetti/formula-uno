package com.gmrossetti.mdp.driver;

public class StrategyParameters {
    private final double minVelocity;
    private final double deviationThreshold;
    private final double brakeDistance;
    private final double accelerateDistance;

    // TODO: dynamic load from JSON
    public StrategyParameters(){
        this.minVelocity = 0.5;
        this.deviationThreshold = 0.5;
        this.brakeDistance = 0.5;
        this.accelerateDistance = 0.5;
    }

//    public StrategyParameters(){
//        this.minVelocity = 0.7;
//        this.deviationThreshold = 0.7;
//        this.brakeDistance = 0.4;
//        this.accelerateDistance = 0.6;
//    }

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
