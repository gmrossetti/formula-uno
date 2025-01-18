package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.model.Circuit;
import com.gmrossetti.mdp.model.Point;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class CircuitView {
    private Pane pane;
    private int spacing = 20;

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

    public Pane getView(){
//        Pane pane = new Pane();
//
//        for (int x = 0; x < this.circuit.getGridWidth(); x++) {
//            for (int y = 0; y < this.circuit.getGridHeight(); y++){
//                PointView pointView = new PointView(this.circuit.getGridPoint(x,y));
//
//                pointView.getView().setTranslateX(this.spacing * y);
//                pointView.getView().setTranslateY(this.spacing * x);
//
//                pane.getChildren().add(pointView.getView());
//            }
//        }
//
//        pane.setStyle("-fx-padding: 20px;");

        return pane;
    }
}
