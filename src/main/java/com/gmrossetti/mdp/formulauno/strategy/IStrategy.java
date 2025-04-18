package com.gmrossetti.mdp.formulauno.strategy;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.driver.IDriver;
import com.gmrossetti.mdp.formulauno.driver.move.Move;

/**
 * Interface representing a strategy for choosing the best move for a driver on a circuit.
 * Implementations of this interface should provide specific strategies for move selection.
 */
public interface IStrategy {
    /**
     * Chooses the best move for the given driver on the specified circuit.
     *
     * @param driver  The driver for whom to choose the best move.
     * @param circuit The circuit on which the driver is racing.
     * @return The best move for the driver on the circuit.
     */
    Move chooseBestMove(IDriver driver, ICircuit circuit);
}
