package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.entity.cartesian.GridLine;

public class HumanCarDriver extends CarDriver {
    public HumanCarDriver(Car car,  Circuit circuit) {
        super(car, circuit.getWaypointsHead());
    }

    public final GridLine makeMove(Move move){
        return super.processMove(move);
    }
}
