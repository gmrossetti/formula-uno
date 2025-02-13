package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.model.GridPoint;
import javafx.scene.layout.Pane;

public class CircuitView extends Pane {
    public final int SPACING = 17;

    public CircuitView(Circuit circuit) {
        this.setStyle("-fx-padding: 20px;");

        for (int y = 0; y < circuit.getGridWidth(); y++) {
            for (int x = 0; x < circuit.getGridHeight(); x++){

                GridPoint gridPoint = circuit.getGridPoint(y,x);

                GridPointView gridPointView = new GridPointView(gridPoint);

                gridPointView.setTranslateX(this.SPACING * gridPoint.x);
                gridPointView.setTranslateY(this.SPACING * gridPoint.y);

                this.getChildren().add(gridPointView);
            }
        }
    }
}
