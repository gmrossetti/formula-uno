package com.gmrossetti.mdp.strategy;

//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StrategyTest {
    @Test
    void testConfiguratorCreates(){
        assertNotNull(StrategyFactory.buildStrategy(StrategyType.BASIC_STRATEGY, new StrategyParameters(0,0,0,0)));



    }
}
