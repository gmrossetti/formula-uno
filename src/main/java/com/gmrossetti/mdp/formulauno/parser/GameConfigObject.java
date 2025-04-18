package com.gmrossetti.mdp.formulauno.parser;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.driver.IDriver;

import java.util.List;

public record GameConfigObject(ICircuit circuit, List<IDriver> botCarDrivers) {
}
