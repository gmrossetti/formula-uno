package com.gmrossetti.mdp.formulauno.driver;

import com.gmrossetti.mdp.formulauno.core.InputHandler;
import com.gmrossetti.mdp.formulauno.cartesian.GridLine;
import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;

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
