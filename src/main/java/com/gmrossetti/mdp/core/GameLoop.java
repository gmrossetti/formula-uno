package com.gmrossetti.mdp.core;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private final Renderer renderer;

    public GameLoop(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void handle(long l) {
        // aggiornamento stato tramite logica



        renderer.update();
    }
}
