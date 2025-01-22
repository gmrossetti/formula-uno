package com.gmrossetti.mdp.controller;

import com.gmrossetti.mdp.model.Point;
import com.gmrossetti.mdp.view.PointView;
import javafx.scene.Node;

public class PointController {
    private Point model;
    private PointView view;

    public PointController(Point point){
        this.model = new Point(point);
        this.view = new PointView(point);
    }
    public Node getView(){
        return this.view.getView();
    }
}
