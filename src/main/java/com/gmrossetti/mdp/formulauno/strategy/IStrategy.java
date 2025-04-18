package com.gmrossetti.mdp.formulauno.strategy;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.driver.IDriver;
import com.gmrossetti.mdp.formulauno.driver.move.Move;

public interface IStrategy {
    Move chooseBestMove(IDriver driver, ICircuit circuit);
}
