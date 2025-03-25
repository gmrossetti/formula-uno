package com.gmrossetti.mdp.parser;

import com.gmrossetti.mdp.driver.StrategyParameters;
import com.gmrossetti.mdp.entity.cartesian.GridPoint;
import com.gmrossetti.mdp.entity.waypoint.BoundaryWaypoint;
import com.gmrossetti.mdp.entity.waypoint.MidWaypoint;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameParser {
    public static GameParseObject parseGameConfigJson(String gameConfigName) {
        final String basePath = "/com/gmrossetti/mdp/";
        final String fileExtension = ".json";

        InputStream inputStream1 = CircuitParser.class.getResourceAsStream(basePath + gameConfigName + fileExtension);

        try {
            JSONTokener tokener = new JSONTokener(inputStream1);

            JSONObject jsonObject = new JSONObject(tokener);

            String circuitName = jsonObject.getString("circuitName");

            JSONArray botDrivers = jsonObject.getJSONArray("botStrategies");

            List<String> strategiesNames = new ArrayList<>();

            for (int i = 0; i < botDrivers.length(); i++) {
                JSONObject botDriver = botDrivers.getJSONObject(i);

                strategiesNames.add(botDriver.getString("strategyName"));
            }

            return new GameParseObject(circuitName, strategiesNames);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}

