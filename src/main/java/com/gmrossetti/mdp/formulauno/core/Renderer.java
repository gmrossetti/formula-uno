package com.gmrossetti.mdp.formulauno.core;

import com.gmrossetti.mdp.formulauno.view.GameView;

/**
 * The Renderer class is responsible for rendering the game state to the view.
 * It takes the current game state and updates the view accordingly.
 */
public class Renderer {
    private final GameState gameState;
    private final GameView view;

    /**
     * Constructor for the Renderer class.
     *
     * @param gameState The current game state to be rendered.
     * @param gameView  The view that will display the game state.
     */
    public Renderer(GameState gameState, GameView gameView) {
        this.gameState = gameState;
        this.view = gameView;
    }

    /**
     * Updates the view with the current game state.
     * This method should be called whenever the game state changes.
     */
    public void update() {
        this.view.update(gameState);
    }
}
