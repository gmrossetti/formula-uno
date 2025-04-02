package com.gmrossetti.mdp.strategy;

import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.driver.CarDriver;

public interface IStrategy {
    CarDriver.Move chooseBestMove(CarDriver carDriver, ICircuit circuit);
}
