package com.gmrossetti.mdp.strategy;

public final class StrategyFactory {
    public static IStrategy buildStrategy(StrategyParameters strategyParameters){
        return new AdvancedStrategy(strategyParameters);
    }
}
