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
            case START -> color = Color.YELLOW;
            case INSIDE -> color = Color.BLUE;
            case OUTSIDE -> color = Color.RED;
            default -> throw new RuntimeException();
        }

        return color;
    }
}
