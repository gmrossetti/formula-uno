package com.gmrossetti.mdp.controller;

import com.gmrossetti.mdp.model.GridPoint;
import com.gmrossetti.mdp.view.GridPointView;
import javafx.scene.Node;

public class GridPointController {
    private final GridPoint model;
    private final GridPointView view;

    public GridPointController(int x, int y, GridPoint.GridPointType type){
        this(new GridPoint(x, y, type));
    }
    public GridPointController(int x, int y, GridPoint.GridPointType type, PlayerController occupiedBy){
        this(new GridPoint(x, y, type, occupiedBy));
    }
    public GridPointController(GridPoint model){
        this.model = new GridPoint(model);
        this.view = new GridPointView(this.model);
    }

    public GridPoint getModel(){
        return this.model;
    }
    public Node getView() {
        return this.view;
    }
}
