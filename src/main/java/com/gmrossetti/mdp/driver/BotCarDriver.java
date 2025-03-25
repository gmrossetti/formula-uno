package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.core.DriverMoveValidator;
import com.gmrossetti.mdp.entity.cartesian.GridLine;

public class BotCarDriver extends CarDriver{
    public Circuit getCircuit() {
        return circuit;
    }
    final Circuit circuit;

    final BotStrategy botStrategy;
    public BotCarDriver(Car car, Circuit circuit, StrategyParameters strategyParameters) {
        super(car, circuit.getWaypointsHead());

        this.circuit = circuit;

        botStrategy = new BotStrategy(this, strategyParameters);
    }

    public final GridLine makeMove(){
        Move move = getNextMove();

        return super.processMove(move);
    }

    private Move getNextMove(){
        return botStrategy.chooseBestMove();
    }
}
