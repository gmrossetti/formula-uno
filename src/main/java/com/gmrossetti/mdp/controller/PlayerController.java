package com.gmrossetti.mdp.controller;

import com.gmrossetti.mdp.model.Player;
import com.gmrossetti.mdp.model.Point;
import com.gmrossetti.mdp.view.PlayerView;
import javafx.scene.Node;

public class PlayerController {

    private final Player model;
    private final PlayerView view;

    public PlayerController() {
        this.model = new Player();
        this.view = new PlayerView();
    }

    public void setPosition(Point point){
        this.model.setPosition(point);
    }

    public Node getView(){
        return this.view.getView(this.model.getPosition());
    }
}
