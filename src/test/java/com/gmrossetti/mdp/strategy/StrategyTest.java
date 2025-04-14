package com.gmrossetti.mdp.strategy;

import com.gmrossetti.mdp.driver.move.Move;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyTest {
    @Test
    void testStrategyFactoryBuildsBasicStrategy() {
        StrategyParameters params = new StrategyParameters(0, 0, 0, 0);
        IStrategy strategy = StrategyFactory.buildStrategy(StrategyType.BASIC_STRATEGY, params);
        assertNotNull(strategy, "StrategyFactory should create a non-null strategy instance");
    }

    @Test
    void testStrategyFactoryBuildsAdvancedStrategy() {
        StrategyParameters params = new StrategyParameters(1, 1, 1, 1);
        IStrategy strategy = StrategyFactory.buildStrategy(StrategyType.ADVANCED_STRATEGY, params);
        assertNotNull(strategy, "StrategyFactory should create a non-null strategy instance");
    }

    @Test
    void testBasicStrategyFunctionality() {
        // Assuming BasicStrategy is a concrete implementation of Strategy
        StrategyParameters params = new StrategyParameters(0, 0, 0, 0);
        IStrategy strategy = StrategyFactory.buildStrategy(StrategyType.BASIC_STRATEGY, params);

        // Add assertions or mock dependencies to test the functionality of the strategy
        assertNotNull(strategy, "BasicStrategy should be created successfully");
        // Additional tests for specific methods in the strategy can be added here
    }

    @Test
    void testAdvancedStrategyFunctionality() {
        // Assuming AdvancedStrategy is a concrete implementation of Strategy
        StrategyParameters params = new StrategyParameters(1, 1, 1, 1);
        IStrategy strategy = StrategyFactory.buildStrategy(StrategyType.ADVANCED_STRATEGY, params);

        // Add assertions or mock dependencies to test the functionality of the strategy
        assertNotNull(strategy, "AdvancedStrategy should be created successfully");
        // Additional tests for specific methods in the strategy can be added here
    }

    @Test
    void testStrategyFactoryHandlesInvalidStrategyType() {
        StrategyParameters params = new StrategyParameters(0, 0, 0, 0);

        // Assuming the factory throws an exception for invalid strategy types
        assertThrows(NullPointerException.class, () -> {
            StrategyFactory.buildStrategy(null, params);
        }, "StrategyFactory should throw an exception for invalid strategy types");

        IStrategy strategy = StrategyFactory.buildStrategy(StrategyType.BASIC_STRATEGY, params);
        assertNotNull(strategy, "StrategyFactory should create a non-null strategy instance");
    }
}
