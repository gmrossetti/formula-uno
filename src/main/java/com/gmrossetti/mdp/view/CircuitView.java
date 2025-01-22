package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.model.Point;
import javafx.scene.layout.Pane;

public class CircuitView {
    private final Pane pane;
    private final int spacing = 20;

    public CircuitView(){
        this.pane = new Pane();
        this.pane.setStyle("-fx-padding: 20px;");
    }

    public void addPoint(Point point){
        PointView pointView = new PointView(point);

        pointView.getView().setTranslateX(this.spacing * point.y);
        pointView.getView().setTranslateY(this.spacing * point.x);

        this.pane.getChildren().add(pointView.getView());
    }

    public Pane getView(){ return pane; }
}
