package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.entity.CircuitGridPoint;
import com.gmrossetti.mdp.entity.GridPoint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class GridPointView extends Circle {
    public GridPointView(CircuitGridPoint circuitGridPoint){
        super(0, 0, 3, getColorFormGridPointType(circuitGridPoint));
    }

    private static Color getColorFormGridPointType(CircuitGridPoint circuitGridPoint){
        Color color;

        switch(circuitGridPoint.type){
            case START -> color = Color.web("#4CAF50");
            case INSIDE -> color = getGradientColor(circuitGridPoint);
            case OUTSIDE -> color = Color.web("#FF4500");
            case END -> color = Color.web("#FFD700");
            default -> throw new RuntimeException();
        }

        return color;
    }

    private static Color getGradientColor(CircuitGridPoint circuitGridPoint){
        // TODO: remove, is only for testing
            ArrayList<GridPoint> waypoints = new ArrayList<>();

        waypoints.add(new GridPoint(18, 5)); // start point
        waypoints.add(new GridPoint(47, 5));
        waypoints.add(new GridPoint(53, 11));
        waypoints.add(new GridPoint(49, 18));
//        waypoints.add(new GridPoint(54, 28));
//        waypoints.add(new GridPoint(52, 35));
        waypoints.add(new GridPoint(55, 32));
        waypoints.add(new GridPoint(36, 39));
        waypoints.add(new GridPoint(32, 28));
        waypoints.add(new GridPoint(23, 24));
        waypoints.add(new GridPoint(10, 29));
        waypoints.add(new GridPoint(4, 22));
        waypoints.add(new GridPoint(3, 12));
        waypoints.add(new GridPoint(8, 5));
        waypoints.add(new GridPoint(17, 5));

            if(waypoints.contains(circuitGridPoint)) return Color.web("#FFaa00");
        // --------------


        if(circuitGridPoint.isCurving != circuitGridPoint.isNarrow)
            return Color.web("#0099cc");

        if(circuitGridPoint.isCurving && circuitGridPoint.isNarrow){
            return Color.web("#006699");
        }

        return Color.web("#ADD8E6");
    }
}
