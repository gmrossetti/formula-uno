package com.gmrossetti.mdp.controller;

import com.gmrossetti.mdp.model.GridPoint;
import com.gmrossetti.mdp.model.Player;
import com.gmrossetti.mdp.model.Point;
import com.gmrossetti.mdp.view.PlayerView;
import javafx.scene.Node;

public class PlayerController {
    private final Player model;
    private final PlayerView view;

    public PlayerController(GridPoint gridPoint) {
        this.model = new Player();
        this.view = new PlayerView(gridPoint);
    }

    public void setPosition(GridPoint gridPoint){
        this.model.setPosition(gridPoint);
        this.view.update(this.model);
    }

    public Node getView(){
        return this.view;
    }
}
