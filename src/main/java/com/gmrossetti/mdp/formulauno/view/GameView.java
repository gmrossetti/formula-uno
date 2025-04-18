package com.gmrossetti.mdp.formulauno.view;

import com.gmrossetti.mdp.formulauno.core.GameState;
import javafx.scene.layout.HBox;

/**
 * GameView is a JavaFX HBox that represents the main view of the game.
 * It contains the race track view and the controls view.
 */
public class GameView extends HBox {
    private RaceView raceView;
    private ControlsView controlsView;

    /**
     * Constructor for GameView.
     * @param gameState The current game state.
     */
    public GameView(GameState gameState) {
        this.raceView = new RaceView(gameState);
        this.controlsView = new ControlsView();

        this.getChildren().addAll(raceView, controlsView);

        this.setStyle("-fx-background-color: #333; -fx-padding: 20px;");
    }

    /**
     * Updates the GameView with the current game state.
     * It updates the race view to reflect the latest positions of the cars.
     *
     * @param gameState The current game state.
     */
    public void update(GameState gameState){
        this.raceView.update(gameState);
    }
}
