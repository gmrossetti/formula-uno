package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.entity.cartesian.CircuitGridPoint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GridPointView extends Circle {
    public GridPointView(CircuitGridPoint circuitGridPoint){
        super(0, 0, 3, getColorFormGridPointType(circuitGridPoint));
    }

    private static Color getColorFormGridPointType(CircuitGridPoint circuitGridPoint){
        Color color;

        switch(circuitGridPoint.type){
            case INSIDE -> color = Color.web("#ADD8E6");
            case OUTSIDE -> color = Color.web("#FF4500");
            default -> throw new RuntimeException();
        }

        return color;
    }
}
