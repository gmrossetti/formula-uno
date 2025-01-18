package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.model.Point;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PointView {
    private final Point point;
    private final Node view;

    public PointView(Point point){
        this.point = point;

        if(point == null){
            this.view = new Circle(5, 5, 3, Color.RED);
            return;
        }

        this.view = new Circle(5, 5, 3, Color.BLUE);
    }

    public Node getView(){
        return this.view;
    }
}
