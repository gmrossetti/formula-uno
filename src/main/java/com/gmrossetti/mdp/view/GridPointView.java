package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.model.GridPoint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GridPointView extends Circle {
    public GridPointView(GridPoint gridPoint){
        super(0, 0, 3, getColorFormGridPointType(gridPoint));
    }

    private static Color getColorFormGridPointType(GridPoint gridPoint){
        Color color;

        switch(gridPoint.type){
            case START -> color = Color.web("#4CAF50");
            case INSIDE -> color = getGradientColor(gridPoint);
            case OUTSIDE -> color = Color.web("#FF4500");
            case END -> color = Color.web("#FFD700");
            default -> throw new RuntimeException();
        }

        return color;
    }

    private static Color getGradientColor(GridPoint gridPoint){

        if(gridPoint.isCurving != gridPoint.isNarrow)
            return Color.web("#0099cc");

        if(gridPoint.isCurving && gridPoint.isNarrow){
            return Color.web("#006699");
        }

        return Color.web("#ADD8E6");
    }
}
