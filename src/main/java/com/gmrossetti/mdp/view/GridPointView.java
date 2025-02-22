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
        if(circuitGridPoint.isCurving != circuitGridPoint.isNarrow)
            return Color.web("#0099cc");

        if(circuitGridPoint.isCurving && circuitGridPoint.isNarrow){
            return Color.web("#006699");
        }

        return Color.web("#ADD8E6");
    }
}
