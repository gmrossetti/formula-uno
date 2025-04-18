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

/**
 * GameConfigParser class that handles the parsing of game configuration files in JSON format.
 * It provides methods to parse the game configuration and extract circuit and bot driver information.
 */
public class GameConfigParser {
    private static final String BASE_PATH = "/com/gmrossetti/mdp/formulauno/";
    private static final String FILE_EXTENSION = ".json";
    private static final double PARAM_DEFAULT_VALUE = 0.5;

    /**
     * Parses the game configuration file and returns a GameConfigObject.
     *
     * @param gameConfigName The name of the game configuration file (without extension).
     * @return A GameConfigObject containing the circuit and bot drivers.
     */
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

    /**
     * Retrieves the InputStream for the game configuration file.
     *
     * @param gameConfigName The name of the game configuration file (without extension).
     * @return An InputStream for the game configuration file.
     */
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

    /**
     * Parses the strategy parameters from the JSON object.
     *
     * @param strategyParams The JSON object containing the strategy parameters.
     * @return A StrategyParameters object containing the parsed parameters.
     */
    private static StrategyParameters parseStrategyParameters(JSONObject strategyParams) {
        double minVelocity = getParameter(strategyParams, "minVelocity");
        double deviationThreshold = getParameter(strategyParams, "deviationThreshold");
        double brakeDistance = getParameter(strategyParams, "brakeDistance");
        double accelerateDistance = getParameter(strategyParams, "accelerateDistance");

        validateParamValues(minVelocity, deviationThreshold, brakeDistance, accelerateDistance);

        return new StrategyParameters(minVelocity, deviationThreshold, brakeDistance, accelerateDistance);
    }

    /**
     * Validates the strategy parameter values to ensure they are within the range [0, 1].
     *
     * @param paramValues The strategy parameter values to validate.
     * @throws IllegalArgumentException if any parameter value is outside the range [0, 1].
     */
    private static void validateParamValues(double... paramValues){
        for (double paramValue : paramValues) {
            System.out.println(paramValue);
            if(paramValue < 0.0 || paramValue > 1.0){
                throw new IllegalArgumentException("All strategy params must be decimal numbers between 0 and 1");
            }
        }
    }

    /**
     * Retrieves a parameter value from the JSON object or returns the default value if not present.
     *
     * @param strategyParams The JSON object containing the strategy parameters.
     * @param key            The key of the parameter to retrieve.
     * @return The parameter value or the default value if not present.
     */
    private static double getParameter(JSONObject strategyParams, String key) {
        return (strategyParams != null && strategyParams.has(key)) ? strategyParams.getDouble(key) : PARAM_DEFAULT_VALUE;
    }
}
