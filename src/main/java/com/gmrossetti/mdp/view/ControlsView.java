package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.core.InputHandler;
import com.gmrossetti.mdp.driver.CarDriver;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ControlsView extends GridPane {
    public ControlsView() {
        this.setHgap(10);  // Distanza orizzontale tra i pulsanti
        this.setVgap(10);  // Distanza verticale tra i pulsanti
        this.setAlignment(Pos.CENTER);  // Allinea la griglia al centro della finestra

        populateGrid();
    }

    private void populateGrid(){
        CarDriver.Move[] moves = CarDriver.Move.values();

        int moveIndex = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                CarDriver.Move move = moves[moveIndex++];

                Button btn = new Button(move.toString());

//                btn.setOnAction(e -> InputHandler.handleInput(move));

                this.add(btn, j, i);
            }
        }

        this.setStyle("-fx-padding: 20px;");
    }
}
