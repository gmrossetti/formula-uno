package com.gmrossetti.mdp.formulauno.parser;

import com.gmrossetti.mdp.formulauno.circuit.CircuitFactory;
import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.driver.BotDriverFactory;
import com.gmrossetti.mdp.formulauno.driver.IDriver;
import com.gmrossetti.mdp.formulauno.strategy.StrategyParameters;
import com.gmrossetti.mdp.formulauno.strategy.StrategyType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameConfigParser {
    private static final String BASE_PATH = "/com/gmrossetti/mdp/formulauno/";
    private static final String FILE_EXTENSION = ".json";
    private static final double PARAM_DEFAULT_VALUE = 0.5;

    public static GameConfigObject parse(String gameConfigName) {
        try (InputStream inputStream = getGameConfigInputStream(gameConfigName)) {
            JSONObject jsonObject = new JSONObject(new JSONTokener(inputStream));
            String circuitName = jsonObject.getString("circuitName");

            ICircuit circuit = CircuitFactory.buildCircuit(circuitName);
            List<IDriver> botCarDrivers = parseBots(jsonObject, circuit);

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

    /**
     * Parses the bots from the JSON object.
     *
     * @param jsonObject The JSON object containing the bot configuration.
     * @param circuit    The circuit to which the bots belong.
     * @return A list of BotCarDriver objects representing the bots.
     */
    private static List<IDriver> parseBots(JSONObject jsonObject, ICircuit circuit) {
        JSONArray bots = jsonObject.getJSONArray("bots");
        List<IDriver> drivers = new ArrayList<>();

        for (Object bot : bots) {
            JSONObject jsonBot = (JSONObject) bot;
            int quantity = jsonBot.getInt("qty");
            StrategyType strategyType = StrategyType.fromValue(jsonBot.getString("strategy"));
            JSONObject strategyParams = jsonBot.has("params") ? jsonBot.getJSONObject("params") : null;

            StrategyParameters strategyParameters = parseStrategyParameters(strategyParams);

            for (int i = 0; i < quantity; i++) {
                drivers.add(BotDriverFactory.build(circuit, strategyType, strategyParameters));
            }
        }

        return drivers;
    }

    private static StrategyParameters parseStrategyParameters(JSONObject strategyParams) {
        double minVelocity = getParameter(strategyParams, "minVelocity");
        double deviationThreshold = getParameter(strategyParams, "deviationThreshold");
        double brakeDistance = getParameter(strategyParams, "brakeDistance");
        double accelerateDistance = getParameter(strategyParams, "accelerateDistance");

        validateParamValues(minVelocity, deviationThreshold, brakeDistance, accelerateDistance);

        return new StrategyParameters(minVelocity, deviationThreshold, brakeDistance, accelerateDistance);
    }

    private static void validateParamValues(double... paramValues){
        for (double paramValue : paramValues) {
            System.out.println(paramValue);
            if(paramValue < 0.0 || paramValue > 1.0){
                throw new IllegalArgumentException("All strategy params must be decimal numbers between 0 and 1");
            }
        }
    }

    private static double getParameter(JSONObject strategyParams, String key) {
        return (strategyParams != null && strategyParams.has(key)) ? strategyParams.getDouble(key) : PARAM_DEFAULT_VALUE;
    }
}
