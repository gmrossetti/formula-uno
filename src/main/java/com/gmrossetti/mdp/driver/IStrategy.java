package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.actor.Circuit;

public interface IStrategy {
    CarDriver.Move chooseBestMove(CarDriver carDriver, Circuit circuit);
}
