package com.gmrossetti.mdp.formulauno.driver;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.driver.move.Move;
import com.gmrossetti.mdp.formulauno.cartesian.GridLine;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;
import com.gmrossetti.mdp.formulauno.strategy.IStrategy;

/**
 * BotDriver class that extends the Driver class.
 * It represents a bot driver in the Formula Uno game.
 */
class BotDriver extends Driver {
    /**
     * The circuit the driver is racing on.
     */
    final ICircuit circuit;

    /**
     * The strategy used by the bot driver.
     */
    final IStrategy strategy;
    /**
     * Constructor for BotDriver.
     *
     * @param pawn      The pawn associated with the driver.
     * @param circuit  The circuit the driver is racing on.
     * @param strategy The strategy used by the bot driver.
     */
    BotDriver(IPawn pawn, ICircuit circuit, IStrategy strategy) {
        super(pawn, circuit.getWaypointsHead());

        this.circuit = circuit;
        this.strategy = strategy;
    }
    @Override
    public final GridLine makeMove(){
        Move move = strategy.chooseBestMove(this, circuit);

        return super.processMove(move);
    }
}
