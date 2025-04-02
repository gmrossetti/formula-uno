package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.pawn.IPawn;
import com.gmrossetti.mdp.entity.cartesian.GridLine;
import com.gmrossetti.mdp.strategy.IStrategy;

public class BotCarDriver extends CarDriver{
    public ICircuit getCircuit() {
        return circuit;
    }
    final ICircuit circuit;

    final IStrategy strategy;
    BotCarDriver(IPawn car, ICircuit circuit, IStrategy strategy) {
        super(car, circuit.getWaypointsHead());

        this.circuit = circuit;
        this.strategy = strategy;
    }

    public final GridLine makeMove(){
        Move move = getNextMove();

        return super.processMove(move);
    }

    private Move getNextMove(){
        return strategy.chooseBestMove(this, circuit);
    }
}
