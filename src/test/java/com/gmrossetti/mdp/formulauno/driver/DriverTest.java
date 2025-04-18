package com.gmrossetti.mdp.formulauno.driver;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.parser.GameConfigObject;
import com.gmrossetti.mdp.formulauno.parser.GameConfigParser;
import com.gmrossetti.mdp.formulauno.strategy.StrategyParameters;
import com.gmrossetti.mdp.formulauno.strategy.StrategyType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DriverTest {
    private static final GameConfigObject gameConfigObject = GameConfigParser.parse("game-config1");
    @Test
    void testDriverFactoryCreatesBasicDriver() {
        IDriver driver = HumanDriverFactory.build(gameConfigObject.circuit());
        assertNotNull(driver, "DriverFactory should create a non-null Driver instance");
    }

    @Test
    void testDriverFactoryCreatesAdvancedDriver() {
        IDriver driver = BotDriverFactory.build(gameConfigObject.circuit(), StrategyType.BASIC_STRATEGY,
                new StrategyParameters(0,0,0,0));
        assertNotNull(driver, "DriverFactory should create a non-null Driver instance");
    }

    @Test
    void testDriverFunctionality() {
        IDriver driver = BotDriverFactory.build(gameConfigObject.circuit(), StrategyType.BASIC_STRATEGY,
                new StrategyParameters(0,0,0,0));
        assertNotNull(driver, "DriverFactory should create a non-null Driver instance");

        assertNotNull(driver, "Driver should be created successfully");

        GridPoint prevPos = driver.getCar().getPosition();

        // Add specific assertions to test the functionality of the Driver
        driver.makeMove();
        assertNotSame(driver.getCar().getPosition(), prevPos, "Driver should have moved after calling move()");
    }
}
