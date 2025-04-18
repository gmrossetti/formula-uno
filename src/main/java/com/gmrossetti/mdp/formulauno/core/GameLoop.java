package com.gmrossetti.mdp.formulauno.core;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private final GameLogic gameLogic;
    private final Renderer renderer;

    public GameLoop(GameLogic gameLogic, Renderer renderer) {
        this.gameLogic = gameLogic;
        this.renderer = renderer;
    }

    @Override
    public void handle(long l) {
        // aggiornamento stato tramite logica
        if(InputHandler.getInstance().hasMove()){
            gameLogic.nextStep();

            GameManager.getInstance().ifGameEndedShowResults();
        }

        renderer.update();
    }
}
