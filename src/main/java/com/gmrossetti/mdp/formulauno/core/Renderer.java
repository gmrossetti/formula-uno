package com.gmrossetti.mdp.formulauno.core;

import com.gmrossetti.mdp.formulauno.view.GameView;

public class Renderer {
    private final GameState gameState;
    private final GameView view;

    public Renderer(GameState gameState, GameView gameView) {
        this.gameState = gameState;
        this.view = gameView;
    }

    public void update() {
        this.view.update(gameState);
    }
}
