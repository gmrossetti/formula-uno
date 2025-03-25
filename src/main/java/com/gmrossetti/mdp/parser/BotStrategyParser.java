package com.gmrossetti.mdp.parser;

import com.gmrossetti.mdp.driver.StrategyParameters;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class BotStrategyParser {
    public static StrategyParameters parseStrategyParamsJson(String botStrategyName) {
        final String basePath = "/com/gmrossetti/mdp/bot-strategies/";
        final String fileExtension = ".json";

        InputStream inputStream1 = CircuitParser.class.getResourceAsStream(basePath + botStrategyName + fileExtension);

        try {
            JSONTokener tokener = new JSONTokener(inputStream1);

            JSONObject jsonObject = new JSONObject(tokener);

            double minVelocity = jsonObject.getDouble("minVelocity");
            double deviationThreshold = jsonObject.getDouble("deviationThreshold");
            double brakeDistance = jsonObject.getDouble("brakeDistance");
            double accelerateDistance = jsonObject.getDouble("accelerateDistance");

            return new StrategyParameters(minVelocity, deviationThreshold, brakeDistance, accelerateDistance);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
