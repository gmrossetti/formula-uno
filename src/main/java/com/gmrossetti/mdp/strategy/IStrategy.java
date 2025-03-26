package com.gmrossetti.mdp.strategy;

import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.driver.CarDriver;

public interface IStrategy {
    CarDriver.Move chooseBestMove(CarDriver carDriver, Circuit circuit);
}
