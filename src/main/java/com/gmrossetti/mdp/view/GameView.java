package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.core.GameState;
import javafx.scene.layout.HBox;

public class GameView extends HBox {
    private RaceView raceView;
    private ControlsView controlsView;
    public GameView(GameState gameState) {
        this.raceView = new RaceView(gameState);
        this.controlsView = new ControlsView();

        this.getChildren().addAll(raceView, controlsView);

        this.setStyle("-fx-background-color: #333; -fx-padding: 20px;");
    }

    public void update(GameState gameState){
        this.raceView.update(gameState);
    }
}
