package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.controller.GridPointController;
import com.gmrossetti.mdp.model.Circuit;
import com.gmrossetti.mdp.model.GridPoint;
import javafx.scene.layout.Pane;

public class CircuitView extends Pane {
    final int SPACING = 17;

    public CircuitView(Circuit circuit) {
        this.setStyle("-fx-padding: 20px;");

        for (int y = 0; y < circuit.getGridWidth(); y++) {
            for (int x = 0; x < circuit.getGridHeight(); x++){

                GridPoint gridPoint = circuit.getGridPoint(y,x);

                GridPointController gridPointController = new GridPointController(gridPoint);

                gridPointController.getView().setTranslateX(this.SPACING * gridPointController.getModel().x);
                gridPointController.getView().setTranslateY(this.SPACING * gridPointController.getModel().y);

                this.getChildren().add(gridPointController.getView());
            }
        }
    }
}
