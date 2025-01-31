package com.gmrossetti.mdp.controller;

import com.gmrossetti.mdp.model.Circuit;
import com.gmrossetti.mdp.view.CircuitView;

public class CircuitController {
    private final Circuit model;
    private final CircuitView view;

    public CircuitController(Circuit model){
        this.model = model;
        this.view = new CircuitView(this.model);
    }

    public Circuit getModel(){
        return this.model;
    }
    public CircuitView getView() {
        return this.view;
    }
}
