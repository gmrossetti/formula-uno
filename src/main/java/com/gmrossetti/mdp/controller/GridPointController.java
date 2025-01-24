package com.gmrossetti.mdp.controller;

import com.gmrossetti.mdp.model.GridPoint;
import com.gmrossetti.mdp.view.GridPointView;
import javafx.scene.Node;

public class GridPointController {
    private GridPoint model;
    private GridPointView view;

    public GridPointController(GridPoint point){
        this.model = new GridPoint(point);
        this.view = new GridPointView(point);
    }
    public Node getView(){
        return this.view.getView();
    }
}
