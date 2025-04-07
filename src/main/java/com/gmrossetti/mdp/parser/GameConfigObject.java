package com.gmrossetti.mdp.parser;

import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.driver.IDriver;

import java.util.List;

public record GameConfigObject(ICircuit circuit, List<IDriver> botCarDrivers) {
}
