package com.gmrossetti.mdp.strategy;

public enum StrategyType {
    BASIC_STRATEGY("basic-strategy"),
    ADVANCED_STRATEGY("advanced-strategy");

    private final String value;

    StrategyType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    public static StrategyType fromValue(String value) {
        for (StrategyType strategy : StrategyType.values()) {
            if (strategy.getValue().equals(value)) {
                return strategy;
            }
        }

        throw new IllegalArgumentException("Unknown StrategyType value: " + value);
    }
}
