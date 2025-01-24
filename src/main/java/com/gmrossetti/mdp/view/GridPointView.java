package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.model.GridPoint;
import com.gmrossetti.mdp.model.Point;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GridPointView {
    private final Node view;

    public GridPointView(GridPoint gridPoint){
        Color color;

        switch(gridPoint.type){
            case START -> color = Color.YELLOW;
            case INSIDE -> color = Color.BLUE;
            case OUTSIDE -> color = Color.RED;
            default -> throw new RuntimeException();
        }

        this.view = new Circle(0, 0, 3, color);
    }

    public Node getView(){
        return this.view;
    }
}
