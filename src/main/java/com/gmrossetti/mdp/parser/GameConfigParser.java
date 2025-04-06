package com.gmrossetti.mdp.parser;

import com.gmrossetti.mdp.circuit.CircuitFactory;
import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.driver.BotCarDriver;
import com.gmrossetti.mdp.driver.BotCarDriverFactory;
import com.gmrossetti.mdp.strategy.StrategyParameters;
import com.gmrossetti.mdp.strategy.StrategyType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameConfigParser {
    public static GameConfigObject parse(String gameConfigName) {
        final String basePath = "/com/gmrossetti/mdp/";
        final String fileExtension = ".json";

        InputStream inputStream = CircuitParser.class.getResourceAsStream(basePath + gameConfigName + fileExtension);

        if(inputStream == null) {
            throw new RuntimeException("GameConfig file not found: " + basePath + gameConfigName + fileExtension);
        }

        try {
            JSONTokener tokener = new JSONTokener(inputStream);

            JSONObject jsonObject = new JSONObject(tokener);

            String circuitName = jsonObject.getString("circuitName");

            // TODO: add Parser Layer between CircuitFactory and GameConfigParser

            final ICircuit circuit = CircuitFactory.buildCircuit(circuitName);

            JSONArray bots = jsonObject.getJSONArray("bots");

            List<BotCarDriver> botCarDrivers = new ArrayList<>();

            for(Object bot:
                    bots){
                JSONObject jsonBot = (JSONObject) bot;

                int quantity = (int) jsonBot.get("qty");
                final String strategyName = (String) jsonBot.get("strategy");

                final StrategyType strategyType = StrategyType.fromValue(strategyName);

                final JSONObject strategyParams = jsonBot.has("strategyParams") ?
                        jsonBot.getJSONObject("params") : null;

                final double minVelocity = strategyParams != null && strategyParams.has("minVelocity") ?
                        strategyParams.getDouble("minVelocity") : 0.5;

                final double deviationThreshold = strategyParams != null && strategyParams.has("deviationThreshold") ?
                        strategyParams.getDouble("deviationThreshold") : 0.5;

                final double brakeDistance = strategyParams != null && strategyParams.has("brakeDistance") ?
                        strategyParams.getDouble("brakeDistance") : 0.5;

                final double accelerateDistance = strategyParams != null && strategyParams.has("accelerateDistance") ?
                        strategyParams.getDouble("accelerateDistance") : 0.5;

                final StrategyParameters strategyParameters = new StrategyParameters(
                            minVelocity, deviationThreshold, brakeDistance, accelerateDistance
                        );

                for (int i = 0; i < quantity; i++) {
                    final BotCarDriver botCarDriver = BotCarDriverFactory.build(circuit, strategyType, strategyParameters);

                    botCarDrivers.add(botCarDriver);
                }
            }

            return new GameConfigObject(circuit, botCarDrivers);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
