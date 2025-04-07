package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.core.InputHandler;
import com.gmrossetti.mdp.pawn.IPawn;
import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.cartesian.GridLine;

class HumanDriver extends Driver {
    private final InputHandler inputHandler;

    HumanDriver(IPawn car, InputHandler inputHandler, ICircuit circuit) {
        super(car, circuit.getWaypointsHead());
        this.inputHandler = inputHandler;
    }

    @Override
    public GridLine makeMove() {
        if(!inputHandler.hasMove()){
            throw new IllegalStateException("Human driver has no move to make");
        }

        return super.processMove(inputHandler.popMove());
    }
}
