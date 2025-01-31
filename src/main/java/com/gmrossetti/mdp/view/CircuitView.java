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

                GridPoint gridPoint = circuit.getGridPoint(x,y);

                GridPointController gridPointController = new GridPointController(gridPoint);

                gridPointController.getView().setTranslateX(this.spacing * gridPointController.getModel().y);
                gridPointController.getView().setTranslateY(this.spacing * gridPointController.getModel().x);

                this.getChildren().add(gridPointController.getView());
            }
        }
    }
}
