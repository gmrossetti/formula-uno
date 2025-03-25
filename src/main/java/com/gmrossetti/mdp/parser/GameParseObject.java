package com.gmrossetti.mdp.parser;

import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.driver.StrategyParameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameParseObject {
    private final Circuit circuit;
    private final List<StrategyParameters> strategyParameters;

    public GameParseObject(String circuitName, List<String> botStrategiesNames) throws IOException {
        this.circuit = new Circuit(circuitName);

        this.strategyParameters = new ArrayList<>();

        for (String botStrategyName:
            botStrategiesNames) {
            strategyParameters.add(BotStrategyParser.parseStrategyParamsJson(botStrategyName));
        }
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public List<StrategyParameters> getStrategyParameters() {
        return strategyParameters;
    }
}
