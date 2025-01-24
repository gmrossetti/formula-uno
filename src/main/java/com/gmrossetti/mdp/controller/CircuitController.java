package com.gmrossetti.mdp.controller;

import com.gmrossetti.mdp.model.Circuit;
import com.gmrossetti.mdp.model.Point;
import com.gmrossetti.mdp.view.PointView;
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

                Point point2add = this.model.getGridPoint(x,y);

                PointView pointView = new PointView(point2add);

                pointView.getView().setTranslateX(this.spacing * point2add.y);
                pointView.getView().setTranslateY(this.spacing * point2add.x);

                this.view.getChildren().add(pointView.getView());
            }
        }
    }

    public Point getRaceStartPoint(){ return this.model.getRaceStartPoint(); }

    public Pane getView() { return this.view; }
}
