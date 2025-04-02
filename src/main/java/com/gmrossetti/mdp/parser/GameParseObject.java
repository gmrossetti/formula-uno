package com.gmrossetti.mdp.parser;

import com.gmrossetti.mdp.actor.CircuitFactory;
import com.gmrossetti.mdp.actor.ICircuit;
import com.gmrossetti.mdp.strategy.StrategyParameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameParseObject {
    private final ICircuit circuit;
    private final List<StrategyParameters> strategyParameters;

    public GameParseObject(String circuitName, List<String> botStrategiesNames) throws IOException {
        this.circuit = CircuitFactory.buildCircuit(circuitName);

        this.strategyParameters = new ArrayList<>();

        for (String botStrategyName:
            botStrategiesNames) {
            strategyParameters.add(BotStrategyParser.parseStrategyParamsJson(botStrategyName));
        }
    }

    public ICircuit getCircuit() {
        return circuit;
    }

    public List<StrategyParameters> getStrategyParameters() {
        return strategyParameters;
    }
}
