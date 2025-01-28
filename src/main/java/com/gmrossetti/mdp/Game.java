package com.gmrossetti.mdp;

import com.gmrossetti.mdp.controller.CircuitController;
import com.gmrossetti.mdp.controller.PlayerController;
import com.gmrossetti.mdp.model.Player;

import java.util.ArrayList;

public class Game {
    private final CircuitController circuitCtrl;
    private final ArrayList<PlayerController> players;

    public Game(){
        circuitCtrl = new CircuitController();
        players = new ArrayList<>();
    }

    public void addPlayer(){
        PlayerController playerCtrl = new PlayerController(circuitCtrl.getModel().getRaceStartPoint());
        playerCtrl.setPosition(circuitCtrl.getModel().getRaceStartPoint());
        players.add(playerCtrl);
    }
}
