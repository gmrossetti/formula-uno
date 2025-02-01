package com.gmrossetti.mdp;

import com.gmrossetti.mdp.controller.PlayerController;
import com.gmrossetti.mdp.model.Circuit;

public class BotDriver {
    private PlayerController playerCtrl;
    private final Circuit circuit;

    public BotDriver(PlayerController playerCtrl, Circuit circuit){
        this.playerCtrl = playerCtrl;
        this.circuit = circuit;
    }


}
