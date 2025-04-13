package com.gmrossetti.mdp.strategy;

import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.driver.IDriver;
import com.gmrossetti.mdp.driver.move.Move;

public interface IStrategy {
    Move chooseBestMove(IDriver driver, ICircuit circuit);
}
