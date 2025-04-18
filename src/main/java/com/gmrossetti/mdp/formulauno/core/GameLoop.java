package com.gmrossetti.mdp.formulauno.core;

import javafx.animation.AnimationTimer;

/**
 * GameLoop class is responsible for the main game loop.
 * It updates the game state and renders the graphics.
 */
public class GameLoop extends AnimationTimer {
    private final GameLogic gameLogic;
    private final Renderer renderer;

    /**
     * Constructor for GameLoop.
     *
     * @param gameLogic The game logic to be used in the loop.
     * @param renderer The renderer to be used for graphics.
     */
    public GameLoop(GameLogic gameLogic, Renderer renderer) {
        this.gameLogic = gameLogic;
        this.renderer = renderer;
    }

    /**
     * The main loop of the game.
     * It updates the game state and renders the graphics.
     *
     * @param l The current time in nanoseconds.
     */
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
