package com.gmrossetti.mdp.parser;

import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.driver.BotCarDriver;

import java.util.List;

public record GameConfigObject(ICircuit circuit, List<BotCarDriver> botCarDrivers) {
}
