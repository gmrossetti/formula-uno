package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.entity.GridLine;

public class HumanCarDriver extends CarDriver {
    public HumanCarDriver(Car car) {
        super(car);
    }

    public final GridLine makeMove(Move move){
        return super.processMove(move);
    }
}
