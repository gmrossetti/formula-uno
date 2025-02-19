package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.entity.GridLine;

public class BotCarDriver extends CarDriver{
    public BotCarDriver(Car car) {
        super(car);
    }

    public final GridLine makeMove(){
        // TODO: add move decision logic
        Move move = Move.BR;

        return super.processMove(move);
    }
}
