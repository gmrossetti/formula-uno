package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.model.Point;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PointView {
    private final Node view;

    public PointView(Point point){
        Color color;

        switch(point.type){
            case START -> color = Color.YELLOW;
            case INSIDE -> color = Color.BLUE;
            case OUTSIDE -> color = Color.RED;
            default -> throw new RuntimeException();
        }

        this.view = new Circle(5, 5, 3, color);
    }

    public Node getView(){
        return this.view;
    }
}
