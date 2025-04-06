package com.gmrossetti.mdp.strategy;

import com.gmrossetti.mdp.strategy.concrete.AdvancedStrategy;
import com.gmrossetti.mdp.strategy.concrete.BasicStrategy;

public final class StrategyFactory {
    public static IStrategy buildStrategy(StrategyType strategyType, StrategyParameters strategyParameters){
        return switch (strategyType) {
            case BASIC_STRATEGY -> new BasicStrategy(strategyParameters);
            case ADVANCED_STRATEGY -> new AdvancedStrategy(strategyParameters);
            default -> throw new UnsupportedOperationException("Invalid StrategyType: " + strategyType.name());
        };
    }
}
