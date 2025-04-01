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
}
