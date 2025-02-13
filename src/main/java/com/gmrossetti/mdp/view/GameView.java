package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.core.GameState;
import javafx.scene.layout.HBox;

public class GameView extends HBox {
    private RaceView raceView;
    private ControlsView controlsView;
    public GameView(RaceView raceView, ControlsView controlsView) {
        super(raceView, controlsView);

        this.raceView = raceView;
        this.controlsView = controlsView;

        this.setPrefSize(1280, 720);
        this.setStyle("-fx-margin: 20px;");
        this.setStyle("-fx-background-color: #333; -fx-margin: 20px; -fx-padding: 20px;");
    }

    public void update(GameState gameState){
        // TODO: check if UI is usable in current state
        // this.controlsView ....

        this.raceView.update(gameState);
    }
}
