package com.gmrossetti.mdp.strategy;

import com.gmrossetti.mdp.strategy.concrete.AdvancedStrategy;
import com.gmrossetti.mdp.strategy.concrete.BasicStrategy;

public final class StrategyFactory {

    /* TODO: add dynamic strategyType support
        buildStrategy(StrategyType strategyType, StrategyParameters strategyParameters)
     */

    // TODO: remove
    static StrategyType[] strategyTypes = new StrategyType[]{StrategyType.BASIC_STRATEGY, StrategyType.ADVANCED_STRATEGY};

    static int index = 0;

    public static IStrategy buildStrategy(StrategyParameters strategyParameters){

        StrategyType strategyType = strategyTypes[index++];

        return switch (strategyType) {
            case BASIC_STRATEGY -> new BasicStrategy(strategyParameters);
            case ADVANCED_STRATEGY -> new AdvancedStrategy(strategyParameters);
            default -> throw new UnsupportedOperationException("Invalid StrategyType: " + strategyType.name());
        };
    }
}
