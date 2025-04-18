package com.gmrossetti.mdp.formulauno.view;

import com.gmrossetti.mdp.formulauno.core.InputHandler;
import com.gmrossetti.mdp.formulauno.driver.move.Move;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.util.HashMap;
import java.util.Map;

/**
 * ControlsView is a JavaFX GridPane that represents the control buttons for a racing game.
 * It displays buttons for different moves and handles user input.
 */
public class ControlsView extends GridPane {
    final static int BTN_SIZE = 50;
    public ControlsView() {
        this.setHgap(10);  // Distanza orizzontale tra i pulsanti
        this.setVgap(10);  // Distanza verticale tra i pulsanti
        this.setAlignment(Pos.CENTER);  // Allinea la griglia al centro della finestra

        populateGrid();
    }

    /**
     * Populates the grid with buttons representing different moves.
     */
    private void populateGrid(){
        Move[] moves = Move.values();

        int moveIndex = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Move move = moves[moveIndex++];

                Button btn = createButton(move);

                InputHandler inputHandler = InputHandler.getInstance();

                btn.setOnAction(e -> inputHandler.stashInput(move));

                this.add(btn, j, i);
            }
        }

        this.setStyle("-fx-padding: 20px;");
    }

    /**
     * Creates a button for a given move.
     *
     * @param move The move to be represented by the button.
     * @return A Button object representing the move.
     */
    private static Button createButton(Move move) {
        Button btn = new Button(getIconFromMove(move));

        btn.setStyle("-fx-font-weight: bold; -fx-font-size: 22px;");

        btn.setMinSize(BTN_SIZE, BTN_SIZE);
        btn.setMaxSize(BTN_SIZE, BTN_SIZE);
        return btn;
    }

    /**
     * Gets the icon representation of a move.
     *
     * @param move The move to be represented.
     * @return A string representing the icon for the move.
     */
    private static String getIconFromMove(Move move){
        Map<Move,String> moveIcon = new HashMap<>();

        moveIcon.put(Move.BL, "↙");
        moveIcon.put(Move.BM, "↓");
        moveIcon.put(Move.BR, "↘");

        moveIcon.put(Move.CL, "←");
        moveIcon.put(Move.CM, "○");
        moveIcon.put(Move.CR, "→");

        moveIcon.put(Move.TL, "↖");
        moveIcon.put(Move.TM, "↑");
        moveIcon.put(Move.TR, "↗");

        return moveIcon.get(move);
    }
}
