package com.gmrossetti.mdp.formulauno.driver;

import com.gmrossetti.mdp.formulauno.core.InputHandler;
import com.gmrossetti.mdp.formulauno.cartesian.GridLine;
import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;

/**
 * HumanDriver class that extends the Driver class.
 * It represents a human driver in the Formula Uno game.
 */
class HumanDriver extends Driver {
    /**
     * The input handler for processing user input.
     */
    private final InputHandler inputHandler;

    /**
     * Constructor for HumanDriver.
     *
     * @param pawn          The pawn associated with the driver.
     * @param inputHandler  The input handler for processing user input.
     * @param circuit      The circuit the driver is racing on.
     */
    HumanDriver(IPawn pawn, InputHandler inputHandler, ICircuit circuit) {
        super(pawn, circuit.getWaypointsHead());
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
