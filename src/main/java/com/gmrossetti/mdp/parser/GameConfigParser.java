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
    private static final String BASE_PATH = "/com/gmrossetti/mdp/";
    private static final String FILE_EXTENSION = ".json";
    private static final double PARAM_DEFAULT_VALUE = 0.5;

    public static GameConfigObject parse(String gameConfigName) {
        try (InputStream inputStream = getGameConfigInputStream(gameConfigName)) {
            JSONObject jsonObject = new JSONObject(new JSONTokener(inputStream));
            String circuitName = jsonObject.getString("circuitName");

            ICircuit circuit = CircuitFactory.buildCircuit(circuitName);
            List<BotCarDriver> botCarDrivers = parseBots(jsonObject, circuit);

            return new GameConfigObject(circuit, botCarDrivers);

        } catch (Exception e) {
            throw new RuntimeException("Error parsing the game config: " + e.getMessage(), e);
        }
    }

    private static InputStream getGameConfigInputStream(String gameConfigName) {
        InputStream inputStream = CircuitParser.class.getResourceAsStream(BASE_PATH + gameConfigName + FILE_EXTENSION);
        if (inputStream == null) {
            throw new RuntimeException("GameConfig file not found: " + BASE_PATH + gameConfigName + FILE_EXTENSION);
        }
        return inputStream;
    }

    private static List<BotCarDriver> parseBots(JSONObject jsonObject, ICircuit circuit) {
        JSONArray bots = jsonObject.getJSONArray("bots");
        List<BotCarDriver> botCarDrivers = new ArrayList<>();

        for (Object bot : bots) {
            JSONObject jsonBot = (JSONObject) bot;
            int quantity = jsonBot.getInt("qty");
            StrategyType strategyType = StrategyType.fromValue(jsonBot.getString("strategy"));
            JSONObject strategyParams = jsonBot.has("strategyParams") ? jsonBot.getJSONObject("strategyParams") : null;

            StrategyParameters strategyParameters = parseStrategyParameters(strategyParams);

            for (int i = 0; i < quantity; i++) {
                botCarDrivers.add(BotCarDriverFactory.build(circuit, strategyType, strategyParameters));
            }
        }

        return botCarDrivers;
    }

    private static StrategyParameters parseStrategyParameters(JSONObject strategyParams) {
        double minVelocity = getParameter(strategyParams, "minVelocity");
        double deviationThreshold = getParameter(strategyParams, "deviationThreshold");
        double brakeDistance = getParameter(strategyParams, "brakeDistance");
        double accelerateDistance = getParameter(strategyParams, "accelerateDistance");

        return new StrategyParameters(minVelocity, deviationThreshold, brakeDistance, accelerateDistance);
    }

    private static double getParameter(JSONObject strategyParams, String key) {
        return strategyParams != null && strategyParams.has(key) ? strategyParams.getDouble(key) : PARAM_DEFAULT_VALUE;
    }
}
