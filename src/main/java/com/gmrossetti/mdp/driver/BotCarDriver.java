package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.entity.cartesian.GridLine;

public class BotCarDriver extends CarDriver{
    public Circuit getCircuit() {
        return circuit;
    }
    final Circuit circuit;

    final IStrategy strategy;
    public BotCarDriver(Car car, Circuit circuit, IStrategy strategy) {
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
