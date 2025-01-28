package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.controller.GridPointController;
import com.gmrossetti.mdp.model.Circuit;
import com.gmrossetti.mdp.model.GridPoint;
import javafx.scene.layout.Pane;

public class CircuitView extends Pane {
    final int spacing = 20;

    public CircuitView(Circuit circuit) {
        this.setStyle("-fx-padding: 20px;");

        for (int x = 0; x < circuit.getGridWidth(); x++) {
            for (int y = 0; y < circuit.getGridHeight(); y++){

                GridPointController point2addModel = circuit.getGridPointCtrl(x,y);

                point2addModel.getView().setTranslateX(this.spacing * point2addModel.getModel().y);
                point2addModel.getView().setTranslateY(this.spacing * point2addModel.getModel().x);

                this.getChildren().add(point2addModel.getView());
            }
        }
    }
}
