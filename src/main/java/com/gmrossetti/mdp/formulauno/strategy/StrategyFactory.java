package com.gmrossetti.mdp.formulauno.strategy;

import com.gmrossetti.mdp.formulauno.strategy.concrete.AdvancedStrategy;
import com.gmrossetti.mdp.formulauno.strategy.concrete.BasicStrategy;

/**
 * Factory class to create instances of IStrategy based on the provided StrategyType.
 * This class is responsible for instantiating the appropriate strategy implementation.
 */
public final class StrategyFactory {
    /**
     * Creates an instance of IStrategy based on the provided StrategyType and parameters.
     *
     * @param strategyType       The type of strategy to be created.
     * @param strategyParameters The parameters required to configure the strategy.
     * @return An instance of the appropriate IStrategy implementation.
     */
    public static IStrategy buildStrategy(StrategyType strategyType, StrategyParameters strategyParameters){
        return switch (strategyType) {
            case BASIC_STRATEGY -> new BasicStrategy(strategyParameters);
            case ADVANCED_STRATEGY -> new AdvancedStrategy(strategyParameters);
        };
    }
}
