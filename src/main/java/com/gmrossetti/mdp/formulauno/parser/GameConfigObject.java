package com.gmrossetti.mdp.formulauno.parser;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.driver.IDriver;

import java.util.List;

/**
 * Represents the configuration of a game, including the circuit and the list of bot car drivers.
 *
 * @param circuit        The circuit object representing the game circuit.
 * @param botCarDrivers  A list of bot car drivers participating in the game.
 */
public record GameConfigObject(ICircuit circuit, List<IDriver> botCarDrivers) {
}
