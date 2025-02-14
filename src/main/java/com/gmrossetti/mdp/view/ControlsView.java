package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.core.InputHandler;
import com.gmrossetti.mdp.driver.CarDriver;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.util.HashMap;
import java.util.Map;

public class ControlsView extends GridPane {
    final static int BTN_SIZE = 50;
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

                Button btn = createButton(move);

                InputHandler inputHandler = InputHandler.getInstance();

                btn.setOnAction(e -> inputHandler.stashInput(move));

                this.add(btn, j, i);
            }
        }

        this.setStyle("-fx-padding: 20px;");
    }

    private static Button createButton(CarDriver.Move move) {
        Button btn = new Button(getIconFromMove(move));

        btn.setStyle("-fx-font-weight: bold; -fx-font-size: 22px;");

        btn.setMinSize(BTN_SIZE, BTN_SIZE);
        btn.setMaxSize(BTN_SIZE, BTN_SIZE);
        return btn;
    }

    private static String getIconFromMove(CarDriver.Move move){
        Map<CarDriver.Move,String> moveIcon = new HashMap<>();

        moveIcon.put(CarDriver.Move.BL, "↙");
        moveIcon.put(CarDriver.Move.BM, "↓");
        moveIcon.put(CarDriver.Move.BR, "↘");

        moveIcon.put(CarDriver.Move.CL, "←");
        moveIcon.put(CarDriver.Move.CM, "○");
        moveIcon.put(CarDriver.Move.CR, "→");

        moveIcon.put(CarDriver.Move.TL, "↖");
        moveIcon.put(CarDriver.Move.TM, "↑");
        moveIcon.put(CarDriver.Move.TR, "↗");

        return moveIcon.get(move);
    }
}
