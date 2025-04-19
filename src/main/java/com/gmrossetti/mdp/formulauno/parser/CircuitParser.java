package com.gmrossetti.mdp.formulauno.parser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.circuit.tile.ITile;
import com.gmrossetti.mdp.formulauno.circuit.tile.Tile;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.BoundaryWaypoint;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.MidWaypoint;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.Waypoint;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * CircuitParser class that handles the parsing of circuit images and waypoints from JSON files.
 * It provides methods to parse images into a grid of tiles and to parse waypoints from JSON data.
 */
public class CircuitParser {
    final static String BASE_PATH = "/com/gmrossetti/mdp/formulauno/circuits/";
    final static String IMG_FILE_EXT = ".bmp";
    final static int SUPPORTED_IMG_WIDTH = 60;
    final static int SUPPORTED_IMG_HEIGHT = 45;

    /**
     * Parses a circuit image into a grid of tiles.
     *
     * @param circuitName The name of the circuit (without file extension).
     * @return A 2D array of ITile representing the circuit grid.
     * @throws IOException If an error occurs while reading the image file.
     */
    public static ITile[][] parseImageToGrid(String circuitName) throws IOException {
        InputStream inputStream = CircuitParser.class.getResourceAsStream(BASE_PATH + circuitName + IMG_FILE_EXT);

        BufferedImage imgBase = ImageIO.read(inputStream);

        if(!validateImgs(imgBase))
            throw new RuntimeException("Circuit file format not valid!");

        ITile[][] grid = new ITile[SUPPORTED_IMG_HEIGHT][SUPPORTED_IMG_WIDTH];

        for (int y = 0; y < SUPPORTED_IMG_HEIGHT; y++) {
            for (int x = 0; x < SUPPORTED_IMG_WIDTH; x++) {
                // Ottieni il colore del pixel (RGB)
                Color colorBase = new Color(imgBase.getRGB(x, y));

                boolean isOnTrack = false;

                if (colorBase.equals(Color.WHITE)) {
                    isOnTrack = true;
                }

                grid[y][x] = new Tile(new GridPoint(x,y),isOnTrack);
            }
        }

        return grid;
    }

    /**
     * Parses waypoints from a JSON file.
     *
     * @param circuitName The name of the circuit (without file extension).
     * @return A list of Waypoint objects representing the parsed waypoints.
     */
    public static List<Waypoint> parseWaypointsJson(String circuitName){
        InputStream inputStream = CircuitParser.class.getResourceAsStream(BASE_PATH + circuitName + ".json");

        try {
            JSONTokener tokener = new JSONTokener(inputStream);

            JSONObject jsonObject = new JSONObject(tokener);

            String name = jsonObject.getString("name");
            System.out.println("Name: " + name);

            List<Waypoint> waypoints = new ArrayList<>();

            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray waypointsToParse = data.getJSONArray("waypoints");

            for (int i = 0; i < waypointsToParse.length(); i++) {
                JSONObject waypointToParse = waypointsToParse.getJSONObject(i);

                JSONObject centerToParse = waypointToParse.getJSONObject("center");
                final GridPoint center = new GridPoint(centerToParse.getInt("x"), centerToParse.getInt("y"));

                String type = waypointToParse.getString("type");

                switch (type) {
                    case "BOUNDARY":
                        final int width = waypointToParse.getInt("width");
                        final int height = waypointToParse.getInt("height");
                        final String boundaryTypeString = waypointToParse.getString("boundaryType");

                        final BoundaryWaypoint.Type boundaryType = BoundaryWaypoint.Type.valueOf(boundaryTypeString);

                        waypoints.add(new BoundaryWaypoint(center, width, height, boundaryType));
                        break;

                    case "MID":
                        final int radius = waypointToParse.getInt("radius");
                        waypoints.add(new MidWaypoint(center, radius));
                        break;

                    default:
                        throw new RuntimeException("Waypoint type not valid.");
                }
            }

            return waypoints;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Validates the dimensions of the circuit image.
     *
     * @param imgBase The BufferedImage representing the circuit image.
     * @return True if the image dimensions are valid, false otherwise.
     */
    private static boolean validateImgs(BufferedImage imgBase){
        final int imgBaseWidth = imgBase.getWidth();
        final int imgBaseHeight = imgBase.getHeight();

        return imgBaseWidth == SUPPORTED_IMG_WIDTH && imgBaseHeight == SUPPORTED_IMG_HEIGHT;
    }
}