package com.gmrossetti.mdp.formulauno.driver;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.driver.move.Move;
import com.gmrossetti.mdp.formulauno.cartesian.GridLine;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;
import com.gmrossetti.mdp.formulauno.strategy.IStrategy;

class BotDriver extends Driver {
    public ICircuit getCircuit() {
        return circuit;
    }
    final ICircuit circuit;
    final IStrategy strategy;
    BotDriver(IPawn car, ICircuit circuit, IStrategy strategy) {
        super(car, circuit.getWaypointsHead());

        this.circuit = circuit;
        this.strategy = strategy;
    }
    @Override
    public final GridLine makeMove(){
        Move move = getNextMove();

        return super.processMove(move);
    }
    private Move getNextMove(){
        return strategy.chooseBestMove(this, circuit);
    }
}
