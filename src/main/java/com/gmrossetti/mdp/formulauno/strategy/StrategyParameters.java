package com.gmrossetti.mdp.formulauno.strategy;

public record StrategyParameters(double minVelocity, double deviationThreshold, double brakeDistance, double accelerateDistance) {
    public StrategyParameters {
        if (minVelocity < 0 || minVelocity > 1 ||
                deviationThreshold < 0 || deviationThreshold > 1 ||
                brakeDistance < 0 || brakeDistance > 1 ||
                accelerateDistance < 0 || accelerateDistance > 1) {
            throw new IllegalArgumentException("All normalized values must be between 0 and 1");
        }
    }
}