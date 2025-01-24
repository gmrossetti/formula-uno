package com.gmrossetti.mdp.controller;

import com.gmrossetti.mdp.model.Circuit;
import com.gmrossetti.mdp.model.GridPoint;
import com.gmrossetti.mdp.view.GridPointView;
import javafx.scene.layout.Pane;

public class CircuitController {

    private final Circuit model;
    private final Pane view;

    final int spacing = 20;

    public CircuitController(){
        this.model = new Circuit();
        this.view = new Pane();

        this.initView();
    }

    public void initView(){
        this.view.setStyle("-fx-padding: 20px;");

        for (int x = 0; x < this.model.getGridWidth(); x++) {
            for (int y = 0; y < this.model.getGridHeight(); y++){

                GridPoint point2add = this.model.getGridPoint(x,y);

                GridPointView gridPointView = new GridPointView(point2add);

                gridPointView.getView().setTranslateX(this.spacing * point2add.y);
                gridPointView.getView().setTranslateY(this.spacing * point2add.x);

                this.view.getChildren().add(gridPointView.getView());
            }
        }
    }

    public GridPoint getRaceStartPoint(){ return this.model.getRaceStartPoint(); }

    public Pane getView() { return this.view; }
}
