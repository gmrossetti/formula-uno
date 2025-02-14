package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.HumanCarDriver;
import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private final GameState gameState;
    private final GameLogic gameLogic;
    private final Renderer renderer;

    public GameLoop(GameState gameState, GameLogic gameLogic, Renderer renderer) {
        this.gameState = gameState;
        this.gameLogic = gameLogic;
        this.renderer = renderer;
    }

    @Override
    public void handle(long l) {
        // aggiornamento stato tramite logica

//        gameLogic.

        HumanCarDriver humanCarDriver = gameState.getHumanCarDriver();

        if(humanCarDriver != null){
            if(InputHandler.getInstance().hasMove()){
                humanCarDriver.makeMove(InputHandler.getInstance().popMove());
            }
        }

        renderer.update();
    }
}
