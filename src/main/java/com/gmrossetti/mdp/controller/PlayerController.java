package com.gmrossetti.mdp.controller;

import com.gmrossetti.mdp.model.Player;
import com.gmrossetti.mdp.view.PlayerView;
import javafx.scene.Node;

public class PlayerController {
    private final Player model;
    private final PlayerView view;

    public PlayerController(Player model) {
        this.model = model;
        this.view = new PlayerView(this.model);
    }

    public void nextMove(Player.Move move){
        this.model.makeMove(move);

        this.view.update(this.model);
    }


    public Player getModel(){
        return this.model;
    }
    public Node getView(){
        return this.view;
    }
}
