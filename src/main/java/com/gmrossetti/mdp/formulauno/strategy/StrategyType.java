package com.gmrossetti.mdp.formulauno.strategy;

/**
 * Enum representing the different types of strategies available in the Formula Uno game.
 * Each strategy type is associated with a specific string value.
 */
public enum StrategyType {
    BASIC_STRATEGY("basic-strategy"),
    ADVANCED_STRATEGY("advanced-strategy");

    private final String value;

    /**
     * Constructor for StrategyType enum.
     *
     * @param value The string value associated with the strategy type.
     */
    StrategyType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    /**
     * Returns the StrategyType corresponding to the provided string value.
     *
     * @param value The string value representing a strategy type.
     * @return The corresponding StrategyType enum constant.
     * @throws IllegalArgumentException if the value does not match any StrategyType.
     */
    public static StrategyType fromValue(String value) {
        for (StrategyType strategy : StrategyType.values()) {
            if (strategy.getValue().equals(value)) {
                return strategy;
            }
        }

        throw new IllegalArgumentException("Unknown StrategyType value: " + value);
    }
}
